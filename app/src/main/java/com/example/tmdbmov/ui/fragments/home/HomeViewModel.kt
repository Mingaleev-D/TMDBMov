package com.example.tmdbmov.ui.fragments.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmov.network.ApiInterface
import com.example.tmdbmov.network.NetworkResponse
import com.example.tmdbmov.network.model.dto.trendingrepo.TrendingResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomeViewModel @Inject constructor(
  val apiInterface: ApiInterface
) : ViewModel() {

  fun getTrending() {
    viewModelScope.launch {
      when (apiInterface.getTrending("ru-RU", 1)) {
        is NetworkResponse.Success -> {
          Log.d("TAG", "getTrending: Success")
        }
        is NetworkResponse.ApiError -> {
          Log.d("TAG", "getTrending: ApiError")
        }
        is NetworkResponse.NetworkError -> {
          Log.d("TAG", "getTrending: NetworkError")
        }
        is NetworkResponse.UnknownError -> {
          Log.d("TAG", "getTrending: UnknownError")
        }
      }
    }

  }
}