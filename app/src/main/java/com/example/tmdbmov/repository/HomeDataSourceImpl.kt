package com.example.tmdbmov.repository

import com.example.tmdbmov.network.ApiInterface
import com.example.tmdbmov.network.ErrorResponse
import com.example.tmdbmov.network.NetworkResponse
import com.example.tmdbmov.network.model.dto.trendingrepo.ResultT
import com.example.tmdbmov.network.model.dto.trendingrepo.TrendingResponse
import com.example.tmdbmov.utils.AppConstants.LANGUAGE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 6/10/2022
 */

class HomeDataSourceImpl @Inject constructor(
  private val apiInterface: ApiInterface
) : HomeDataSource {
  override suspend fun getListOfMovies(
    dispatcher: CoroutineDispatcher,
    homeResultCallback: (result: NetworkResponse<List<List<ResultT>>, ErrorResponse>) -> Unit
  ) {
    withContext(dispatcher) {
      try {
        val trendingMoviesResponse = async { apiInterface.getTrending(LANGUAGE, 1) }
        val upcomingMoviesResponse = async { apiInterface.getUpcoming(LANGUAGE, 1) }
        val popularMoviesResponse = async { apiInterface.getPopular(LANGUAGE, 1) }
        val topRatedMoviesResponse = async { apiInterface.getTopRated(LANGUAGE, 1) }

        processData(
          homeResultCallback,
          trendingMoviesResponse.await(),
          upcomingMoviesResponse.await(),
          popularMoviesResponse.await(),
          topRatedMoviesResponse.await()
        )
      } catch (e: Exception) {
        throw e
      }
    }
  }

  private fun processData(
    homeResultCallback: (result: NetworkResponse<List<List<ResultT>>, ErrorResponse>) -> Unit,
    trending: NetworkResponse<TrendingResponse, ErrorResponse>,
    upcoming: NetworkResponse<TrendingResponse, ErrorResponse>,
    popular: NetworkResponse<TrendingResponse, ErrorResponse>,
    topRated: NetworkResponse<TrendingResponse, ErrorResponse>
  ) {
    val pair1 = buildResponse(trending)
    val pair2 = buildResponse(upcoming)
    val pair3 = buildResponse(popular)
    val pair4 = buildResponse(topRated)

    when {
      pair1.first == null -> {
        pair1.second?.let { homeResultCallback(it) }
        return
      }
      pair2.first == null -> {
        pair2.second?.let { homeResultCallback(it) }
        return
      }
      pair2.first == null -> {
        pair2.second?.let { homeResultCallback(it) }
        return
      }
      pair2.first == null -> {
        pair2.second?.let { homeResultCallback(it) }
        return
      }
      else -> {
        val resultList = ArrayList<List<ResultT>>()
        pair1.first?.let { resultList.add(it) }
        pair2.first?.let { resultList.add(it) }
        pair3.first?.let { resultList.add(it) }
        pair4.first?.let { resultList.add(it) }
        homeResultCallback(NetworkResponse.Success(resultList))
      }
    }
  }

  private fun buildResponse(response: NetworkResponse<TrendingResponse, ErrorResponse>)
    : Pair<List<ResultT>?, NetworkResponse<List<List<ResultT>>, ErrorResponse>?> {
    return when (response) {
      is NetworkResponse.Success -> {
        Pair(response.body.results, null)
      }
      is NetworkResponse.ApiError -> {
        Pair(null, NetworkResponse.ApiError(response.body, response.code))
      }
      is NetworkResponse.NetworkError -> {
        Pair(null, NetworkResponse.NetworkError(IOException()))
      }
      is NetworkResponse.UnknownError -> {
        Pair(null, NetworkResponse.UnknownError(Throwable()))
      }
    }
  }
}