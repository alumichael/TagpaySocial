package com.aegletec.tagpaysocial.ui.home.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope

import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.aegletec.tagpaysocial.R
import com.aegletec.tagpaysocial.data.Userpreference
import com.aegletec.tagpaysocial.data.localdb.db_model.AssignedBeneficiariesItem
import com.aegletec.tagpaysocial.data.localdb.db_model.AssignedBeneficiariesLocal
import com.aegletec.tagpaysocial.data.network.Resource
import com.aegletec.tagpaysocial.databinding.DashboardFragmentBinding
import com.aegletec.tagpaysocial.ui.handleApiError
import com.aegletec.tagpaysocial.ui.home.HomeViewModel
import com.aegletec.tagpaysocial.ui.home.adapter.BoardCardsAdapter
import com.github.loadingview.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.coroutines.launch

import javax.inject.Inject


@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.dashboard_fragment) {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: DashboardFragmentBinding
    private lateinit var viewManager: GridLayoutManager
    private var view_id: Int = 0
    private lateinit var mProgressDialog: LoadingDialog
    private lateinit var projectuuid: String
    private lateinit var uuid: String
    private lateinit var device_id: String
    private var total_beneficiaries: Int = 0
    private var total_communities: Int = 0
    private var total_payment: Int = 0
    private var total_update_beneficiaries: Int = 0
    private var total_update_communities: Int = 0
    private var total_update_beneficialpayments: Int = 0
    lateinit var realm: Realm


    @Inject
    lateinit var userpreference: Userpreference


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DashboardFragmentBinding.bind(view)
        viewManager = GridLayoutManager(requireActivity(), 2)
        // mProgressDialog = LoadingDialog.get(requireActivity())
        viewModel.getBoardcardsViewModel()
        mProgressDialog = LoadingDialog.get(requireActivity())
        realm = Realm.getDefaultInstance()

        observedDatastore()
        // initView()

        viewModel.boardcards.observe(requireActivity(), Observer {
            //   mProgressDialog.progressbar(true,requireActivity())

            try {
                lifecycleScope.launch {
                    with(binding) {
                        recyclerviewBoardcards.layoutManager = viewManager
                        recyclerviewBoardcards.itemAnimator = DefaultItemAnimator()
                        Log.i("data", it.toString())
                        val cardAdapter = BoardCardsAdapter(viewModel, it, requireContext())
                        recyclerviewBoardcards.adapter = cardAdapter
                        cardAdapter.onItemClick = { cardoption ->
                            // do something with your item
                            //Log.d("TAG", cardoption.card_name)
                            when (cardoption.card_name) {
                                "Assign NFC" -> {
                                    view_id = R.id.action_dashboardFragment_to_assignNFCFragment

                                }

                                "Make Payment" -> {
                                    view_id = R.id.action_dashboardFragment_to_makePaymentFragment

                                }
                                "Transaction History" -> {
                                    view_id = R.id.action_dashboardFragment_to_transactHistoryFragment

                                }
                                "Sync Status" -> {
                                    view_id = R.id.action_dashboardFragment_to_syncStatusFragment

                                }
                                "Settings" -> {
                                    view_id = R.id.action_dashboardFragment_to_settingFragment

                                }
                                "Profile" -> {
                                    view_id = R.id.action_dashboardFragment_to_profileFragment

                                }

                            }

                            nextFragment(view_id, view)
                        }

                        if (total_beneficiaries == 0) {
                            viewModel.getAssignedbeneficials(projectuuid, uuid, device_id)
                        }

                        viewModel.get_assigns_beneficials.observe(requireActivity(), Observer {
                            lifecycleScope.launch {
                                mProgressDialog.show()
                                when (it) {
                                    is Resource.Success -> {
                                        // mProgressDialog.hide()
                                        viewModel.saveAssignedBeneficials(it.value)
                                        Log.d("assigned_Size", it.value.size.toString())
                                        Log.d("assigned_value", it.value.toString())
                                        userpreference.saveTotalAssignBeneficials(it.value.size)

                                        viewModel.getAssignedCommunities(projectuuid, uuid, device_id)
                                        viewModel.get_assigns_communities.observe(requireActivity(), Observer {
                                            lifecycleScope.launch {

                                                when (it) {
                                                    is Resource.Success -> {
                                                        viewModel.saveAssignedCommunities(it.value)
                                                        userpreference.saveTotalAssignCommnities(it.value.size)

                                                        viewModel.getAssignedBeneficialPayment(projectuuid, uuid, device_id)
                                                        viewModel.get_assigns_beneficial_payments.observe(requireActivity(), Observer {
                                                            lifecycleScope.launch {

                                                                when (it) {
                                                                    is Resource.Success -> {
                                                                        mProgressDialog.hide()
                                                                        viewModel.saveAssignedBeneficialPayment(it.value)
                                                                        userpreference.saveTotalAssignBeneficialPayment(it.value.size)

                                                                    }

                                                                    is Resource.Failure -> {
                                                                        mProgressDialog.hide()
                                                                        handleApiError(it, userpreference = userpreference)

                                                                    }
                                                                }

                                                            }
                                                        })


                                                    }

                                                    is Resource.Failure -> {
                                                        mProgressDialog.hide()
                                                        handleApiError(it, userpreference = userpreference)

                                                    }
                                                }


                                            }
                                        })


                                    }

                                    is Resource.Failure -> {
                                        mProgressDialog.hide()
                                        handleApiError(it, userpreference = userpreference)

                                    }


                                }

                            }

                        })

                    }

                }
            } catch (e: Exception) {
                e.message?.let { it1 -> Log.d("TAG", it1) }
                // handleApiError(findViewById(R.id.homepage),e.message)
            }
        })


    }

    private fun observedDatastore() {

        userpreference.project_uuid().asLiveData().observe(requireActivity(), Observer {
            projectuuid = it
        })
        userpreference.uuid().asLiveData().observe(requireActivity(), Observer {
            uuid = it
        })
        userpreference.device_id().asLiveData().observe(requireActivity(), Observer {
            device_id = it
        })
        userpreference.totalBeneficials().asLiveData().observe(requireActivity(), Observer {
            total_beneficiaries = it

            userpreference.totalBeneficialPayment().asLiveData().observe(requireActivity(), Observer {
                total_payment = it

                userpreference.totalCommunities().asLiveData().observe(requireActivity(), Observer {
                    total_communities = it


                    userpreference.updatedBeneficials().asLiveData().observe(requireActivity(), Observer {
                        total_update_beneficiaries = it

                        userpreference.updatedAssignbeneficialPayment().asLiveData().observe(requireActivity(), Observer {
                            total_update_beneficialpayments = it

                            userpreference.updatedCommunities().asLiveData().observe(requireActivity(), Observer {
                                total_update_communities = it

                                initView()

                            })


                        })

                    })

                })


            })

        })


    }

    private fun initView() {
        //set all count text
        with(binding) {

            val beneficialCountTxt = "$total_update_beneficiaries/$total_beneficiaries"
            beneficiaryCount.text = beneficialCountTxt

            val communityCountTxt = "$total_update_communities/$total_communities"
            communityCount.text = communityCountTxt

            val paymentCountTxt = "$total_update_beneficialpayments/$total_payment"
            paymentCount.text = paymentCountTxt

            if (total_beneficiaries != 0) {
                val mprogress_value = (total_update_beneficiaries / total_beneficiaries * 100).toFloat()
                Log.d("progress", mprogress_value.toString())
                seekBarBeneficial.progress = mprogress_value
            }

            if(total_communities != 0){
                val mprogress_value = (total_update_communities / total_communities * 100).toFloat()
                Log.d("progress", mprogress_value.toString())
                seekBarCommunities.progress = mprogress_value
            }

            if(total_payment != 0){
                val mprogress_value = (total_update_beneficialpayments / total_payment * 100).toFloat()
                Log.d("progress", mprogress_value.toString())
                seekBarPaymentItem.progress = mprogress_value
            }

        }

        realm.executeTransaction { realm: Realm ->
            val beneficial: AssignedBeneficiariesItem? =
                    realm.where(AssignedBeneficiariesItem::class.java)
                            .equalTo("nfcUid", "c53181e9")
                            .findFirstAsync()

            Log.d("queryData", beneficial?.age.toString())


        }

    }

    fun nextFragment(view_id: Int, view: View) {
        Navigation.findNavController(view).navigate(view_id)
        // snackbar(title,view)
        // Toast.makeText(context, title, Toast.LENGTH_LONG).show()
    }
}