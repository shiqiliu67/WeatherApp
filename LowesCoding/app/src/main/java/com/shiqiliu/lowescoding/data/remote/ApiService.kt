package com.shiqiliu.lowescoding.data.remote

import com.shiqiliu.lowescoding.data.reponse.CityResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/forecast")
    suspend fun getCityWeather(
        @Query("q") q:String//city name
    ):Response<CityResponse>
}