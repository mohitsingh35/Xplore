package com.ncs.xplore

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ncs.xplore.ExtensionsUtil.visible
import com.ncs.xplore.databinding.ActivityMainBinding
import com.ncs.xplore.databinding.ActivityTravelDestinationsBinding

class TravelDestinationsActivity : AppCompatActivity(),DestinationsAdapter.OnCardClick {
    lateinit var binding : ActivityTravelDestinationsBinding
    lateinit var adapter: DestinationsAdapter
    private val recyclerView: RecyclerView by lazy {
        binding.recyclerView
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTravelDestinationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val destinationList = listOf(
            DestinationModel(id = "1", title = "Dehradun", desc = "Land of Himalayas", rating = 4.6F, price = 7000, imgUrl = "https://www.shutterstock.com/image-photo/these-mountains-makes-dehradun-beautiful-600nw-1470377021.jpg", personCount = 1, daysCount = 1),
            DestinationModel(id = "1", title = "Shimla", desc = "Land of Himalayas", rating = 4.6F, price = 7000, imgUrl = "https://www.shutterstock.com/image-photo/these-mountains-makes-dehradun-beautiful-600nw-1470377021.jpg", personCount = 1, daysCount = 1),
            DestinationModel(id = "1", title = "Kasol", desc = "Land of Himalayas", rating = 4.6F, price = 7000, imgUrl = "https://www.shutterstock.com/image-photo/these-mountains-makes-dehradun-beautiful-600nw-1470377021.jpg", personCount = 1, daysCount = 1),
            DestinationModel(id = "1", title = "Dehradun", desc = "Land of Himalayas", rating = 4.6F, price = 7000, imgUrl = "https://www.shutterstock.com/image-photo/these-mountains-makes-dehradun-beautiful-600nw-1470377021.jpg", personCount = 1, daysCount = 1),
            DestinationModel(id = "1", title = "Shimla", desc = "Land of Himalayas", rating = 4.6F, price = 7000, imgUrl = "https://www.shutterstock.com/image-photo/these-mountains-makes-dehradun-beautiful-600nw-1470377021.jpg", personCount = 1, daysCount = 1),
            DestinationModel(id = "1", title = "Kasol", desc = "Land of Himalayas", rating = 4.6F, price = 7000, imgUrl = "https://www.shutterstock.com/image-photo/these-mountains-makes-dehradun-beautiful-600nw-1470377021.jpg", personCount = 1, daysCount = 1),
            )

        setRecyclerView(destinationList.toMutableList())

    }

    private fun setRecyclerView(destList: MutableList<DestinationModel>) {
        adapter = DestinationsAdapter(destList, this)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        recyclerView.visible()
    }

    override fun onCardClicked() {
        TODO("Not yet implemented")
    }
}