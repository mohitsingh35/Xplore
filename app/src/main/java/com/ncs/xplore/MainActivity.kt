package com.ncs.xplore

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ncs.xplore.ExtensionsUtil.gone
import com.ncs.xplore.ExtensionsUtil.setOnClickThrottleBounceListener
import com.ncs.xplore.ExtensionsUtil.visible
import com.ncs.xplore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var currentIndex=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.next.setOnClickThrottleBounceListener{
            if (currentIndex<3){
                currentIndex++
                renderViews()
            }

        }
        binding.previous.setOnClickThrottleBounceListener {
            if (currentIndex>1){
                currentIndex--
                renderViews()
            }
        }
        binding.nextintent.setOnClickThrottleBounceListener {
            startActivity(Intent(this,TravelDestinationsActivity::class.java))
        }

    }
    private fun renderViews(){
        when(currentIndex){
            1->{
                binding.destinationInput.visible()
                binding.noofpeopleInput.gone()
                binding.budgetInput.gone()
                binding.nextintent.gone()
                binding.previous.visible()
                binding.next.visible()
            }
            2->{
                binding.destinationInput.gone()
                binding.noofpeopleInput.visible()
                binding.budgetInput.gone()
                binding.nextintent.gone()
                binding.previous.visible()
                binding.next.visible()
            }
            3->{
                binding.destinationInput.gone()
                binding.noofpeopleInput.gone()
                binding.budgetInput.visible()
                binding.nextintent.visible()
                binding.previous.visible()
                binding.next.visible()
            }
        }
    }
}