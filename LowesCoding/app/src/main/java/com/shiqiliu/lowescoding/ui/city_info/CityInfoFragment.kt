package com.shiqiliu.lowescoding.ui.city_info

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.shiqiliu.lowescoding.R
import com.shiqiliu.lowescoding.databinding.FragmentCityInfoBinding
import com.shiqiliu.lowescoding.util.LogUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityInfoFragment : Fragment() {
    lateinit var binding: FragmentCityInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityInfoBinding.inflate(layoutInflater, container, false)
        initView()
        clickEvent()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val viewModel = ViewModelProvider(requireActivity()).get(CityInfoViewModel::class.java)
        viewModel.cityInfo.observe(viewLifecycleOwner) {
                LogUtil.e(TAG, "initView: $it")
            binding.tvClearTemp.text =
                "Temp: ${viewModel.convertToF(it.list[0].main.temp)} °F"//today's weather
            binding.tvCloudyTemp.text = "Temp: ${viewModel.convertToF(it.list[0].main.temp_min)}°F"
            binding.tvRainTemp.text = "Temp: ${viewModel.convertToF(it.list[0].main.temp_max)}°F"
            binding.tvWeatherTemp.text = it.list[0].weather[0].main
        }
    }

    private fun clickEvent() {
        binding.root.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CityWeatherDetailFragment())
                .addToBackStack("detail")
                .commit()
        }
    }

    companion object {
        private const val TAG = "CityInfoFragment"
    }
}