package com.example.tmdbmov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tmdbmov.databinding.ActivityMainBinding
import com.example.tmdbmov.di.MainComponent
import com.example.tmdbmov.di.MyApplication

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  lateinit var mainComponent: MainComponent

  override fun onCreate(savedInstanceState: Bundle?) {
    mainComponent = (applicationContext as MyApplication).appComponent.mainComponent().create()
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }
}