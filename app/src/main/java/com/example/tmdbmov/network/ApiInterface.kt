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

  @GET("movie/upcoming")
  suspend fun getUpcoming(
    @Query("language") language: String,
    @Query("page") page: Int
  ): NetworkResponse<TrendingResponse, ErrorResponse>

  @GET("movie/popular")
  suspend fun getPopular(
    @Query("language") language: String,
    @Query("page") page: Int
  ): NetworkResponse<TrendingResponse, ErrorResponse>

  @GET("movie/top_rated")
  suspend fun getTopRated(
    @Query("language") language: String,
    @Query("page") page: Int
  ): NetworkResponse<TrendingResponse, ErrorResponse>
}