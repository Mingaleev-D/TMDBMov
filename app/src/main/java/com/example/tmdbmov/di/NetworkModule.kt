package com.example.tmdbmov.di

import com.example.tmdbmov.network.ApiInterface
import com.example.tmdbmov.utils.AppConstants.API_KEY
import com.example.tmdbmov.utils.AppConstants.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author : Mingaleev D
 * @data : 4/10/2022
 */
@Module
class NetworkModule {
  @Singleton
  @Provides
  fun providesInterceptor(): Interceptor {
    return Interceptor { chain ->
      val newUrl = chain.request().url
        .newBuilder()
        .addQueryParameter("api_key", API_KEY)
        .build()

      val newRequest = chain.request()
        .newBuilder()
        .url(newUrl)
        .build()

      chain.proceed(newRequest)
    }
  }

  @Singleton
  @Provides
  fun loggingClient(authInterceptor: Interceptor): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
      .readTimeout(15, TimeUnit.SECONDS)
      .connectTimeout(15, TimeUnit.SECONDS)
      .addNetworkInterceptor(interceptor)
      .addNetworkInterceptor(authInterceptor)
      .build()
  }

  @Singleton
  @Provides
  fun providesRetrofitInstance(logginClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(logginClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Singleton
  @Provides
  fun movieApi(retrofit: Retrofit): ApiInterface {
    return retrofit.create(ApiInterface::class.java)
  }
}