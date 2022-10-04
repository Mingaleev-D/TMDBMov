package com.example.tmdbmov.network.model.dto.trendingrepo


import com.google.gson.annotations.SerializedName

data class TrendingResponse(
  @SerializedName("page")
    val page: Int,
  @SerializedName("results")
    val results: List<ResultT>,
  @SerializedName("total_pages")
    val totalPages: Int,
  @SerializedName("total_results")
    val totalResults: Int
)