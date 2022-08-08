package com.shiqiliu.lowescoding.ui.city_info

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shiqiliu.lowescoding.data.reponse.CityResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class CityInfoViewModel @Inject constructor() : ViewModel() {
    val cityInfo = MutableLiveData<CityResponse>()
    fun convertToC(temp: Double) = temp - 273.15
    fun convertToF(temp: Double)=((((1.8) * (convertToC(temp)) + 32)*100.0).roundToInt())/100.0
    @SuppressLint("SimpleDateFormat")
    fun convertToDateTime(time:Int):String {
        val format = "yyyy.MM.dd HH:mm"
        val sdf = SimpleDateFormat(format)
        return sdf.format(Date(time.toLong()*1000))
    }
    fun convertToDate(time: Int):String{
        val format = "yyyy.MM.dd"
        val sdf = SimpleDateFormat(format)
        return sdf.format(Date(time.toLong()*1000))
    }
    fun convertToKm(meter:Int)=meter/1000
}