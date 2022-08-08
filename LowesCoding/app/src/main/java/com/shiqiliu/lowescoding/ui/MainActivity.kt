package com.shiqiliu.lowescoding.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.shiqiliu.lowescoding.R
import com.shiqiliu.lowescoding.databinding.ActivityMainBinding
import com.shiqiliu.lowescoding.ui.city_info.CityInfoActivity
import com.shiqiliu.lowescoding.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        clickEvent()
    }

    private fun initView() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
    }

    private fun clickEvent() {
        try {
            binding.btnCheck.setOnClickListener {
                val cityName = binding.etCityName.text.toString()
                if (cityName.isNotEmpty()) {
                    getResponse(cityName)
                } else {
                    Toast.makeText(baseContext, "Please enter a city name", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getResponse(cityName: String) {
        try {
            viewModel.successResponse.observe(this) {
                if (it != null) {
                    val intent = Intent(this@MainActivity, CityInfoActivity::class.java)
                    intent.putExtra(Constants.cityResponse, it)
                    startActivity(intent)
                    viewModel.clearData()
                }
            }
            viewModel.failedResponse.observe(this) {
                if (it!=null){
                    Toast.makeText(baseContext, it, Toast.LENGTH_SHORT).show()
                }
                viewModel.clearData()
            }
            viewModel.getCityWeather(cityName)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}