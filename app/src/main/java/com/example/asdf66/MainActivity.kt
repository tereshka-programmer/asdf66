package com.example.asdf66

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.example.asdf66.Model.Modell
import com.example.asdf66.Model.Weather
import com.example.asdf66.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var weather: Modell

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getWeather()
    }

    fun getWeather(){
        GlobalScope.launch(Dispatchers.IO) {
            binding.progressBar.isVisible = true
            weather = try {
                OkHttp.getData()
            } catch (e: IOException){
                return@launch
            }
            runOnUiThread {
                kotlin.run {
                    binding.progressBar.isVisible = false
                    binding.tvTemp.text = (weather.main.temp - 273).toInt().toString()
                    binding.tvTempFL.text = (weather.main.feels_like - 273).toInt().toString()
                    binding.tvWeend.text = weather.wind.speed.toString() + " m/s"
                    binding.tvHum.text = weather.main.humidity.toString() + "%"
                }
            }

        }
    }
}