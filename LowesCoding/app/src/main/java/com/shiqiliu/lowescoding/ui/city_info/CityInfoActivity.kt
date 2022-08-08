package com.shiqiliu.lowescoding.ui.city_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.shiqiliu.lowescoding.R
import com.shiqiliu.lowescoding.data.reponse.CityResponse
import com.shiqiliu.lowescoding.databinding.ActivityCityInfoBinding
import com.shiqiliu.lowescoding.util.Constants
import com.shiqiliu.lowescoding.util.LogUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCityInfoBinding
    private val viewModel: CityInfoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    private fun initView(){
        //get intent
        val result = intent.getSerializableExtra(Constants.cityResponse) as CityResponse
        //set window
        window.statusBarColor = ContextCompat.getColor(this,R.color.darker_gray)
        //set toolbar
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar?.let {
            it.title = result.city.name
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_back)
        }
        //store in view model
        viewModel.cityInfo.postValue(result)
        LogUtil.e(TAG, "initView: result:$result")
        //init frag
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, CityInfoFragment())
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    companion object{
        private const val TAG = "CityInfoActivity"
    }
}