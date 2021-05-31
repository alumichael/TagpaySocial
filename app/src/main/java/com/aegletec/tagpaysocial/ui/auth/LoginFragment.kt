package com.aegletec.tagpaysocial.ui.auth

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.aegletec.tagpaysocial.R
import com.aegletec.tagpaysocial.data.Userpreference
import com.aegletec.tagpaysocial.data.api_models.LoginPost
import com.aegletec.tagpaysocial.data.network.Resource
import com.aegletec.tagpaysocial.databinding.LoginFragmentBinding
import com.aegletec.tagpaysocial.ui.*
import com.aegletec.tagpaysocial.ui.home.HomeActivity
import com.aegletec.tagpaysocial.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm
import kotlinx.android.synthetic.main.login_fragment.view.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.login_fragment) {

    private lateinit var binding: LoginFragmentBinding
    private lateinit var model_name:String
    private lateinit var imei_no:String
    private lateinit var email:String
    private lateinit var password:String
   // private lateinit var userpreference: Userpreference
    lateinit var realm : Realm

    @Inject
    private lateinit var userpreference: Userpreference


    private val viewModel by viewModels<AuthViewModel>()
    private val viewModelHome by viewModels<HomeViewModel>()
    private val args: LoginFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LoginFragmentBinding.bind(view)
        realm = Realm.getDefaultInstance()


        binding.logEmail.editText?.setText(args.email)

        binding.submitBtn.setOnClickListener {

            realm.executeTransaction { realm: Realm ->
                viewModelHome.getUser()
            }


        }

        viewModelHome.userInfoResponse.observe(requireActivity(), Observer {
            lifecycleScope.launch {
                try {
                   // snackbar(it.firstName.toString(), findViewById(R.id.homepage))
                    it.firstName.toString()
                    with(binding){
                        email=logEmail.edit_email.text.toString().trim()
                        password=logPassword.edit_password.text.toString().trim()
                        model_name=Build.MODEL
                        imei_no=requestIMEI()

                        Log.d("device_id",imei_no)
                        Log.d("device_model",model_name)
                        //snackbar(requestIMEI()+"  "+model_name,view)
                        validation(email,password,model_name,imei_no,view)

                    }


                } catch (e: Exception) {

                    handleAllError(view, "Update your device detail")
                    navigateToRegister(view)
                    userpreference.setLoggedin(false)

                }
            }

        })

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {

            binding.submitBtn.visible(false)
            binding.progressBar.visible(it is Resource.Loading)

            when(it){
                is Resource.Success->{
                   lifecycleScope.launch {
                       binding.submitBtn.visible(true)
                       binding.progressBar.visible(false)

                       viewModel.saveAccessTokens(it.value.token)
                       viewModel.saveDeviceID(imei_no)
                       viewModel.saveUUID(it.value.uuid)
                       viewModel.saveProjectUUID(it.value.projectUuid)

                       requireActivity().fromLoginToHomeActivity(HomeActivity::class.java,view)
                   }
                }
                is Resource.Failure -> {
                    binding.submitBtn.visible(true)
                    binding.progressBar.visible(false)

                    handleApiError(it) {
                    validation(email,password,model_name,imei_no,view)
                    }
                }
            }
        })



        binding.registerScreen.setOnClickListener {

            lifecycleScope.launch{
                //Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment)
                navigateToRegister(view)
            }
        }

    }

    fun validation(email:String,password:String,model_name:String,imei_no:String,view: View){
        if (email.isEmpty()){
            binding.logEmail.error="Please enter email"
        }else if(password.isEmpty()){
            binding.logPassword.error="Please enter password"
        }else if (model_name.isEmpty()){
            snackbar("Something went wrong",view)
            Log.i("Model name", "Null model name")
        }else if (imei_no.isEmpty()){
            snackbar("Something went wrong",view)
            Log.i("Android ID ", "Null android_id")
        }else{

            viewModel.login(LoginPost(email,imei_no,model_name,password,true))

        }

    }


    override fun onDestroy() {
        super.onDestroy()
        realm.close()

    }







}