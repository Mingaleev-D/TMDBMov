package com.example.tmdbmov.network

import com.example.tmdbmov.network.model.dto.trendingrepo.TrendingResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author : Mingaleev D
 * @data : 4/10/2022
 */

interface ApiInterface {
  @GET("trending/movie/day")
 suspend fun getTrending(
    @Query("language") language: String,
    @Query("page") page: Int
  ): NetworkResponse<TrendingResponse, ErrorResponse>
}