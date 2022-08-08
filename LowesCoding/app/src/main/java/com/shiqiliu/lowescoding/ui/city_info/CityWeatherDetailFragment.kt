package com.shiqiliu.lowescoding.ui.city_info

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.shiqiliu.lowescoding.databinding.FragmentCityWeatherDetailBinding

class CityWeatherDetailFragment : Fragment() {
    private lateinit var binding: FragmentCityWeatherDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCityWeatherDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    @SuppressLint("SetTextI18n")
    private fun initView(){
        val viewModel = ViewModelProvider(requireActivity())[CityInfoViewModel::class.java]
        viewModel.cityInfo.observe(viewLifecycleOwner) {
            //weather
            binding.tvMainWeather.text = "${viewModel.convertToF(it.list[0].main.temp)}°F"
            binding.tvFeelWeather.text = "Feels like: ${viewModel.convertToF(it.list[0].main.feels_like)}°F"
            val h = viewModel.convertToF(it.list[0].main.temp_max)
            val l = viewModel.convertToF(it.list[0].main.temp_min)
            binding.tvRange.text = "H: $h°F, L: $l°F"

            binding.tvCity.text = it.city.name
            binding.tvDate.text = viewModel.convertToDate(it.list[0].dt)
            //list
            val sunset = viewModel.convertToDateTime(it.city.sunset)
            val sunrise = viewModel.convertToDateTime(it.city.sunrise)
            Log.e(TAG, "initView: sunset:$sunset, sunrise:$sunrise, time:${it.city.sunset.toLong()}")
            binding.tvSunrise.text ="Sunrise: $sunrise"
            binding.tvSunset.text = "Sunset: $sunset"
            binding.tvDescription.text = it.list[0].weather[0].description
            binding.tvWeather.text = it.list[0].weather[0].main
            binding.tvPressure.text = "Pressure: ${it.list[0].main.pressure} hPa"
            binding.tvVisibility.text = "Visibility: ${viewModel.convertToKm(it.list[0].visibility)} km"
            binding.tvWind.text ="Wind Speed: ${it.list[0].wind.speed} m/s"
        }
    }
    companion object {
     private const val TAG = "CityWeatherDetail"
    }
}