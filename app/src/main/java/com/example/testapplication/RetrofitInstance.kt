package com.example.testapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This is the retrofit instance that is used with the base url
 *
 * @author Nishikanta
 */
object RetrofitInstance {
    val api: TestApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://picsum.photos/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TestApi::class.java)
    }
}