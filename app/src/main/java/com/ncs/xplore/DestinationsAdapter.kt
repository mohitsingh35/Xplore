package com.ncs.xplore

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.ncs.xplore.ExtensionsUtil.setOnClickThrottleBounceListener
import com.ncs.xplore.databinding.TravelDestEachItemBinding

class DestinationsAdapter constructor(
    private var destinationsList: MutableList<DestinationModel>,
    private var onCardClick: OnCardClick
) : RecyclerView.Adapter<DestinationsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            TravelDestEachItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position:Int) {
        val destination = destinationsList[position]

        Glide.with(holder.itemView.context)
            .load(destination.imgUrl)
            .listener(object : RequestListener<Drawable> {

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .encodeQuality(80)
            .override(40, 40)
            .apply(
                RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            )
            .into(holder.binding.image)

        holder.binding.name.text = destination.title
        holder.binding.desc.text = destination.desc
        holder.binding.rating.text = "${destination.rating} ⭐"
        holder.binding.price.text="Estd. ${destination.price} |"
        holder.binding.personCount.text="  ${destination.personCount.toString()}"
        holder.binding.daysCount.text="  ${destination.personCount.toString()}"
        holder.binding.parent.setOnClickThrottleBounceListener{
            onCardClick.onCardClicked()
        }

    }

    override fun getItemCount(): Int {
        return destinationsList.size
    }

    fun updateList(newList: List<DestinationModel>) {
        destinationsList = newList.toMutableList()
        notifyDataSetChanged()
    }
    inner class ViewHolder(val binding: TravelDestEachItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnCardClick{
        fun onCardClicked()
    }

}
