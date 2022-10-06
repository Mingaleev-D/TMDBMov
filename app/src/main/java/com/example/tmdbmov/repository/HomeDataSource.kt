package com.example.tmdbmov.repository

import com.example.tmdbmov.network.ErrorResponse
import com.example.tmdbmov.network.NetworkResponse
import com.example.tmdbmov.network.model.dto.trendingrepo.ResultT
import kotlinx.coroutines.CoroutineDispatcher

/**
 * @author : Mingaleev D
 * @data : 6/10/2022
 */

interface HomeDataSource {
  suspend fun getListOfMovies(
    dispatcher: CoroutineDispatcher,
    homeResultCallback: (result: NetworkResponse<List<List<ResultT>>, ErrorResponse>) -> Unit
  )

}