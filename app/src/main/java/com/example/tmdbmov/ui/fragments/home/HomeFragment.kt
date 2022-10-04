package com.example.tmdbmov.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbmov.MainActivity
import com.example.tmdbmov.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment() {

  private var mBinding: FragmentHomeBinding? = null
  private val binding get() = mBinding!!

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    (requireActivity() as MainActivity).mainComponent.inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    mBinding = FragmentHomeBinding.inflate(layoutInflater)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.getTrending()
  }

  override fun onDestroy() {
    super.onDestroy()
    mBinding = null
  }


}