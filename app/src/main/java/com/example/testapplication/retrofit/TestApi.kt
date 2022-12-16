package com.example.testapplication.retrofit

import com.example.testapplication.model.ListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This is our api interface class where we write all the api that is being used in our application
 *
 * @author Nishikanta
 */
interface TestApi {
    @GET("v2/list?")
    fun getList(@Query("page") page : String,@Query("limit") limit : String) : Call<List<ListModel>>
}