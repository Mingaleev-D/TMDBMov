package com.example.tmdbmov.ui.fragments.home

import androidx.lifecycle.ViewModel
import com.example.tmdbmov.network.ApiInterface
import com.example.tmdbmov.network.model.dto.trendingrepo.TrendingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomeViewModel @Inject constructor(
 val apiInterface: ApiInterface
) : ViewModel() {

  fun getTrending(){
    apiInterface.getTrending("ru-RU",1).enqueue(object :Callback<TrendingResponse>{
      override fun onResponse(call: Call<TrendingResponse>, response: Response<TrendingResponse>) {
        if(response.isSuccessful){

        }
      }

      override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
        //TODO logd faille.toString()
      }

    })
  }
}