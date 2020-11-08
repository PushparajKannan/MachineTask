package com.sample.machinetask.ui.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.sample.machinetask.R
import com.sample.machinetask.adapter.DataAdapter
import com.sample.machinetask.databinding.FragmentHomeBinding
import com.sample.machinetask.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment(){

    private lateinit var binding: FragmentHomeBinding


    private val homeViewModel: HomeViewModel by lazy {
        val factory = HomeViewModel.Factory(requireContext())
        ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        binding.viewModel = homeViewModel
        binding.fragment = this
        binding.lifecycleOwner = this



        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagingAdapter = DataAdapter(requireContext())
        binding.list.adapter = pagingAdapter

        lifecycleScope.launch {
            homeViewModel.doggosPagingFlow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }
}
