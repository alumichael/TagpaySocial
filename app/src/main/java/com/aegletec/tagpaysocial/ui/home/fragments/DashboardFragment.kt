package com.aegletec.tagpaysocial.ui.home.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aegletec.tagpaysocial.R
import com.aegletec.tagpaysocial.databinding.DashboardFragmentBinding
import com.aegletec.tagpaysocial.ui.handleAllError
import com.aegletec.tagpaysocial.ui.home.HomeViewModel
import com.aegletec.tagpaysocial.ui.home.adapter.BoardCardsAdapter
import com.aegletec.tagpaysocial.ui.snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.Exception

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.dashboard_fragment) {
  /*  companion object {
        fun newInstance() = DashboardFragment()
    }*/

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: DashboardFragmentBinding
    private lateinit var viewManager : GridLayoutManager



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=DashboardFragmentBinding.bind(view)
        viewManager = GridLayoutManager(requireActivity(),2)

        viewModel.getBoardcardsViewModel()



        viewModel.boardcards.observe(requireActivity(), Observer {
            try {
                lifecycleScope.launch {
                    with(binding){
                        recyclerviewBoardcards.layoutManager = viewManager
                        recyclerviewBoardcards.itemAnimator=DefaultItemAnimator()
                       // recyclerviewBoardcards.addItemDecoration((requireContext(),2))
                        Log.i("data",it.toString())
                        val cardAdapter=BoardCardsAdapter(viewModel, it, requireContext())
                        recyclerviewBoardcards.adapter= cardAdapter

                        cardAdapter.onItemClick = { cardoption ->

                            // do something with your item
                            Log.d("TAG", cardoption.card_name)
                            nextFragment(cardoption.card_name,view)
                        }
                    }

                }
            }catch (e: Exception){

                //handleAllError(findViewById(R.id.homepage),e.message)

            }
        })

    }

    fun nextFragment(title: String,view: View){

        snackbar(title,view)
       // Toast.makeText(context, title, Toast.LENGTH_LONG).show()

    }
}