package com.example.tmdbmov.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

/**
 * @author : Mingaleev D
 * @data : 4/10/2022
 */
@Singleton
@Component(modules = [MainModule::class, NetworkModule::class, ViewModelBuilderModule::class, SubComponentsModule::class])
interface ApplicationComponent {
  @Component.Factory
  interface Factory {
    fun create(@BindsInstance applicationContext: Context): ApplicationComponent
  }

  fun mainComponent(): MainComponent.Factory
}

@Module(subcomponents = [MainComponent::class])
object SubComponentsModule