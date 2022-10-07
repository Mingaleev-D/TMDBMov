package com.example.tmdbmov.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmov.R
import com.example.tmdbmov.di.IoDispatcher
import com.example.tmdbmov.network.ApiInterface
import com.example.tmdbmov.network.NetworkResponse
import com.example.tmdbmov.network.model.dto.trendingrepo.ResultT
import com.example.tmdbmov.repository.HomeDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
  private val apiInterface: ApiInterface,
  private val homeDataSource: HomeDataSource,
  @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
  private val _listsOfMovies: MutableLiveData<List<List<ResultT>>> = MutableLiveData()
  val listsOfMovies: LiveData<List<List<ResultT>>> = _listsOfMovies

  private val _errorMessage: MutableLiveData<String?> = MutableLiveData()
  val errorMessage: LiveData<String?> = _errorMessage

  private val _errorMessageVisibility: MutableLiveData<Boolean> = MutableLiveData()
  val errorMessageVisibility: LiveData<Boolean> = _errorMessageVisibility

  private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
  val isLoading: LiveData<Boolean> = _isLoading

  fun getListOfMovies() {
    showErrorMessage(false)
    try {
      viewModelScope.launch {
        homeDataSource.getListOfMovies(dispatcher) { result ->
          when (result) {
            is NetworkResponse.Success -> {
              _listsOfMovies.value = result.body
              _isLoading.value = false
              _errorMessageVisibility.value = false
            }
            is NetworkResponse.NetworkError -> {
              showErrorMessage(true, R.string.error_connection.toString())
            }
            is NetworkResponse.ApiError -> {
              showErrorMessage(true, R.string.resource_not_found.toString())
            }
            is NetworkResponse.UnknownError -> {
              showErrorMessage(true, R.string.unexpected_error.toString())
            }
          }

        }
      }
    } catch (e: Exception) {
      throw e
    }
  }

  private fun showErrorMessage(show: Boolean, message: String? = null) {
    _isLoading.value = !show
    _errorMessageVisibility.value = show
    _errorMessage.value = message
  }
}