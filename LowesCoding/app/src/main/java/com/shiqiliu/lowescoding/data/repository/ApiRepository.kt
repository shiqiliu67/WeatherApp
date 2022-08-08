package com.shiqiliu.lowescoding.data.repository

import com.shiqiliu.lowescoding.data.remote.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getCityWeather(cityName:String) = apiService.getCityWeather(cityName)
}