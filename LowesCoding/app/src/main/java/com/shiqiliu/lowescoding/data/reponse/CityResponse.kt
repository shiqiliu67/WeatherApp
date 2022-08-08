package com.shiqiliu.lowescoding.data.reponse

import java.io.Serializable
data class CityResponse(
    var city: City,
    var cnt: Int,
    var cod: String,
    var list: List<DayWeather>,
    var message: Int
):Serializable

data class City(
    var coord: Coord,
    var country: String,
    var id: Int,
    var name: String,
    var population: Int,
    var sunrise: Int,
    var sunset: Int,
    var timezone: Int
):Serializable

data class DayWeather(
    var clouds: Clouds,
    var dt: Int,
    var dt_txt: String,
    var main: Main,
    var pop: Double,
    var rain: Rain,
    var sys: Sys,
    var visibility: Int,
    var weather: List<Weather>,
    var wind: Wind
):Serializable

data class Coord(
    var lat: Double,
    var lon: Double
):Serializable

data class Clouds(
    var all: Int
):Serializable

data class Main(
    var feels_like: Double,
    var grnd_level: Int,
    var humidity: Int,
    var pressure: Int,
    var sea_level: Int,
    var temp: Double,
    var temp_kf: Double,
    var temp_max: Double,
    var temp_min: Double
):Serializable

data class Rain(
    var `3h`: Double
):Serializable

data class Sys(
    var pod: String
):Serializable

data class Weather(
    var description: String,
    var icon: String,
    var id: Int,
    var main: String
):Serializable

data class Wind(
    var deg: Int,
    var gust: Double,
    var speed: Double
):Serializable