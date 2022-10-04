package com.example.tmdbmov.di

import android.app.Application

/**
 * @author : Mingaleev D
 * @data : 4/10/2022
 */

class MyApplication : Application() {
  lateinit var appComponent: ApplicationComponent

  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerApplicationComponent.factory().create(this)
  }
}