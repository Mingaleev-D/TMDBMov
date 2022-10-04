package com.example.tmdbmov.di

import com.example.tmdbmov.MainActivity
import com.example.tmdbmov.ui.fragments.home.HomeFragment
import dagger.Subcomponent

/**
 * @author : Mingaleev D
 * @data : 4/10/2022
 */
@Subcomponent(modules = [])
interface MainComponent {
  @Subcomponent.Factory
  interface Factory {
    fun create(): MainComponent
  }

  fun inject(activity: MainActivity)
  fun inject(fragment: HomeFragment)
}