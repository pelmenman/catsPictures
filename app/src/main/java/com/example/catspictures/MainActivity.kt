package com.example.catspictures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.catspictures.databinding.ActivityMainBinding
import com.example.catspictures.model.CatImage
import com.example.catspictures.model.CatService
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var catService: CatService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        catService = CatService.create()

        setContentView(binding.root)


        binding.button.setOnClickListener {
            val result = catService.getImage()

            result.enqueue(
                object: Callback<CatImage> {

                    override fun onResponse(call: Call<CatImage>, response: Response<CatImage>) {
//                        if (response.body() != null) {
//                            for (picture in response.body()!!) {
//                                Picasso.get().load(picture.url).into(binding.image)
//                            }
//                        }

                        if (response.body() != null) Picasso.get().load(response.body()!!.first().url).into(binding.image)
                    }

                    override fun onFailure(call: Call<CatImage>, t: Throwable) {
                       Toast.makeText(applicationContext, "Getting image error. Try again", Toast.LENGTH_SHORT).show()
                    }

                }
            )

        }

    }
}

