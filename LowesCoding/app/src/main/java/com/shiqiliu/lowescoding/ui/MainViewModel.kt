package com.shiqiliu.lowescoding.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiqiliu.lowescoding.data.reponse.CityResponse
import com.shiqiliu.lowescoding.data.repository.ApiRepository
import com.shiqiliu.lowescoding.util.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository : ApiRepository):ViewModel() {
    val successResponse = MutableLiveData<CityResponse?>()
    val failedResponse = MutableLiveData<String?>()
    //get api response
    fun getCityWeather(cityName:String){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val response = repository.getCityWeather(cityName)
                if (response.isSuccessful){
                    successResponse.postValue(response.body())
                    LogUtil.e(TAG, "getCityWeather: ${response.body()}")
                }
                else{
                    failedResponse.postValue("unable to get the response, please check the network or enter a valid city name")
                }
            }
            catch (e:Exception){
                e.printStackTrace()
                failedResponse.postValue("failed.")
            }
        }
    }

    fun clearData(){
        successResponse.postValue(null)
        failedResponse.postValue(null)
    }

    companion object{
        private const val TAG ="MainViewModel"
    }
}