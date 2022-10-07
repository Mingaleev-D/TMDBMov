package com.example.tmdbmov.di

import com.example.tmdbmov.repository.HomeDataSource
import com.example.tmdbmov.repository.HomeDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * @author : Mingaleev D
 * @data : 7/10/2022
 */
@Module
abstract class DataModule {
  @Singleton
  @Binds
  abstract fun provideHomeDataSource(dataSourceImpl: HomeDataSourceImpl):HomeDataSource
}