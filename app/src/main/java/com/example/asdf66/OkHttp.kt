package com.example.asdf66

import com.example.asdf66.Model.Modell
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

object OkHttp {
    private val contentType = "application/json; charset=utf-8".toMediaType()
    private val client = OkHttpClient()
    private val gson = Gson()

    fun getData() : Modell{
        val request = Request.Builder()
            .url("https://api.openweathermap.org/data/2.5/weather?q=minsk&appid=99b9b3b73fb174b0420e372e9c4ebd45")
            .build()

        val weather: Modell

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected")
            weather = gson.fromJson(response.body!!.string(), Modell::class.java)
        }

        return weather
    }
}

