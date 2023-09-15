package com.example.catspictures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.catspictures.databinding.ActivityMainBinding
import com.example.catspictures.model.CatImageItem
import com.example.catspictures.model.CatService
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.thecatapi.com/v1/"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val catService = retrofit.create(CatService::class.java)

        binding.button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = catService.getImage()

                runOnUiThread {
                    Picasso.get().load(result.first().url).into(binding.image)
                }
            }
        }

    }
}

