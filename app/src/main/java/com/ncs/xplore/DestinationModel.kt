package com.ncs.xplore

data class DestinationModel(
    val id:String,
    val title:String,
    val desc:String,
    val rating:Float,
    val price: Int,
    val imgUrl:String,
    val personCount:Int,
    val daysCount:Int
    ) {
}