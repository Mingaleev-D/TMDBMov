package com.example.tmdbmov.network.model.dto.trendingrepo


import com.google.gson.annotations.SerializedName

data class TrendingResponse(
  @SerializedName("results")
    val results: List<ResultT>

)