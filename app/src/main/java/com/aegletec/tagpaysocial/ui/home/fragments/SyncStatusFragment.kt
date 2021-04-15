package com.aegletec.tagpaysocial.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.aegletec.tagpaysocial.R
import com.aegletec.tagpaysocial.databinding.DashboardFragmentBinding
import com.aegletec.tagpaysocial.databinding.SyncStatusFragmentBinding
import com.aegletec.tagpaysocial.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SyncStatusFragment : Fragment(R.layout.sync_status_fragment) {
  /*  companion object {
        fun newInstance() = DashboardFragment()
    }*/



    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: SyncStatusFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=SyncStatusFragmentBinding.bind(view)



    }
}