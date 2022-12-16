package com.example.testapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapplication.model.ListModel
import com.example.testapplication.retrofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *
 * This is the main list view model where the api call is being made to send the list data to the
 * UI
 *
 * @author Nishikanta
 */
class ListViewModel : ViewModel() {
    private var listLiveData = MutableLiveData<List<ListModel>>()
    fun getPopularMovies(page: String, limit: String) {
        CoroutineScope(Dispatchers.IO).launch {
            RetrofitInstance.api.getList(page, limit)
                .enqueue(object : Callback<List<ListModel>> {
                    override fun onResponse(
                        call: Call<List<ListModel>>,
                        response: Response<List<ListModel>>
                    ) {
                        if (response.body() != null) {
                            listLiveData.value = response.body()
                            Log.d("TAG", response.body().toString())
                        } else {
                            return
                        }
                    }

                    override fun onFailure(call: Call<List<ListModel>>, t: Throwable) {
                        Log.d("TAG", t.message.toString())
                    }
                })
        }

    }

    fun observeMovieLiveData(): LiveData<List<ListModel>> {
        return listLiveData
    }
}
