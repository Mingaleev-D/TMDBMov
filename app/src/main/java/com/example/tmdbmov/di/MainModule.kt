package com.example.tmdbmov.di

import androidx.lifecycle.ViewModel
import com.example.tmdbmov.ui.fragments.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author : Mingaleev D
 * @data : 4/10/2022
 */
@Module
interface MainModule {
  @Binds
  @IntoMap
  @ViewModelKey(HomeViewModel::class)
  fun bindHoeViewModel(homeViewModel: HomeViewModel): ViewModel
}