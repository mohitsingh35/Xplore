package com.ncs.xplore

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_SLIDE
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


object ExtensionsUtil {



    // Visibililty Extensions

    fun View.gone() = run { visibility = View.GONE }
    fun View.visible() = run { visibility = View.VISIBLE }
    fun View.invisible() = run { visibility = View.INVISIBLE }

    infix fun View.visibleIf(condition: Boolean) =
        run { visibility = if (condition) View.VISIBLE else View.GONE }

    infix fun View.goneIf(condition: Boolean) =
        run { visibility = if (condition) View.GONE else View.VISIBLE }

    infix fun View.invisibleIf(condition: Boolean) =
        run { visibility = if (condition) View.INVISIBLE else View.VISIBLE }



    // Toasts

    fun Fragment.toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    fun Fragment.toast(@StringRes message: Int) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    fun Activity.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun Activity.toast(@StringRes message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


    //Snackbar

    fun View.snackbar(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(this, message, duration).setAnimationMode(ANIMATION_MODE_SLIDE).show()
    }

    fun View.snackbar(@StringRes message: Int, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(this, message, duration).show()
    }


    fun Activity.hideKeyboard() {
        val imm: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = currentFocus ?: View(this)
        imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    fun Fragment.hideKeyboard() {
        activity?.apply {
            val imm: InputMethodManager =
                getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            val view = currentFocus ?: View(this)
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    fun Fragment.showKeyboard(editBox: EditText) {
        activity?.apply {
            val imm: InputMethodManager =
                getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInputFromInputMethod(editBox.windowToken, InputMethodManager.SHOW_FORCED)
        }
    }

    fun EditText.showKeyboardB() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        requestFocus()
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }


    // Convert px to dp
    val Int.dp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()

    //Convert dp to px
    val Int.px: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()


    val String.isDigitOnly: Boolean
        get() = matches(Regex("^\\d*\$"))

    val String.isAlphabeticOnly: Boolean
        get() = matches(Regex("^[a-zA-Z]*\$"))

    val String.isAlphanumericOnly: Boolean
        get() = matches(Regex("^[a-zA-Z\\d]*\$"))


    //Null check
    val Any?.isNull get() = this == null

    fun Any?.ifNull(block: () -> Unit) = run {
        if (this == null) {
            block()
        }
    }

    /**
     * Set Drawable to the left of EditText
     * @param icon - Drawable to set
     */
    fun EditText.setDrawable(icon: Drawable) {
        this.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null)
    }


    /**
     * Function to run a delayed function
     * @param millis - Time to delay
     * @param function - Function to execute
     */
    fun runDelayed(millis: Long, function: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(function, millis)
    }

    /**
     * Show multiple views
     */
    fun showViews(vararg views: View) {
        views.forEach { view -> view.visible() }
    }


    /**
     * Hide multiple views
     */
    fun hideViews(vararg views: View) {
        views.forEach { view -> view.gone() }
    }


    //Date formatting
    fun String.toDate(format: String = "yyyy-MM-dd HH:mm:ss"): Date? {
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
        return dateFormatter.parse(this)
    }

    fun Date.toStringFormat(format: String = "yyyy-MM-dd HH:mm:ss"): String {
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
        return dateFormatter.format(this)
    }



    fun View.setMargins(left: Int, top: Int, right: Int, bottom: Int) {
        if (this.layoutParams is ViewGroup.MarginLayoutParams) {
            val params = this.layoutParams as ViewGroup.MarginLayoutParams
            params.setMargins(left, top, right, bottom);
        }
    }

    fun TextInputEditText.appendTextAtCursorMiddleCursor(textToAppend: String, type: Int) {
        val position = this.selectionStart
        val text = this.text
        val newText = StringBuilder(text).insert(position, textToAppend).toString()
        this.setText(newText)
        if (type == 2) this.setSelection(position + 1)
        else this.setSelection(position + 2)
    }







    fun deleteDownloadedFile(downloadID : Long, context: Context) {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.remove(downloadID)
    }

    fun getVersionName(context: Context): String? {
        return try {
            val packageInfo: PackageInfo =
                context.packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            null
        }
    }

    fun String.isGreaterThanVersion(otherVersion: String): Boolean {
        val thisParts = this.split(".").map { it.toInt() }
        val otherParts = otherVersion.split(".").map { it.toInt() }

        for (i in 0 until maxOf(thisParts.size, otherParts.size)) {
            val thisPart = thisParts.getOrNull(i) ?: 0
            val otherPart = otherParts.getOrNull(i) ?: 0

            if (thisPart != otherPart) {
                return thisPart > otherPart
            }
        }

        return false
    }

    fun View.setOnClickThrottleBounceListener(throttleTime: Long = 600L, onClick: () -> Unit) {

        this.setOnClickListener(object : View.OnClickListener {

            private var lastClickTime: Long = 0
            override fun onClick(v: View) {
                context?.let {
                    v.bounce(context)
                    if (SystemClock.elapsedRealtime() - lastClickTime < throttleTime) return
                    else onClick()
                    lastClickTime = SystemClock.elapsedRealtime()
                }

            }
        })
    }

    fun View.bounce(context: Context, animDuration: Long = 500L) = run {
        this.clearAnimation()
        val animation = AnimationUtils.loadAnimation(context, R.anim.bounce)
            .apply {
                duration = animDuration
            }
        this.startAnimation(animation)
    }


    fun View.setSingleClickListener(throttleTime: Long = 600L, action: () -> Unit) {
        this.setOnClickListener(object : View.OnClickListener {
            private var lastClickTime: Long = 0

            override fun onClick(v: View) {
                if (SystemClock.elapsedRealtime() - lastClickTime < throttleTime) return
                else action()
                lastClickTime = SystemClock.elapsedRealtime()
            }
        })
    }


    fun View.setBackgroundColorRes(colorResId: Int) {
        val color = context.resources.getColor(colorResId)
        setBackgroundColor(color)
    }

    fun View.setBackgroundColor(color: Int) {
        background = ColorDrawable(color)
    }

    fun View.setBackgroundDrawable(drawable: Drawable) {
        background = drawable
    }
}

