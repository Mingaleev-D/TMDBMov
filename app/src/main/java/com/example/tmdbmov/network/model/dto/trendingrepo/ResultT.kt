package com.example.tmdbmov.network.model.dto.trendingrepo


import com.google.gson.annotations.SerializedName

data class ResultT(
  @SerializedName("backdrop_path")
  val backdropPath: String,
  @SerializedName("id")
  val id: Int,
  @SerializedName("poster_path")
  val posterPath: String,
  @SerializedName("title")
  val title: String
)