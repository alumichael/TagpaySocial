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
import com.aegletec.tagpaysocial.R
import com.aegletec.tagpaysocial.data.api_models.RegisterDevice
import com.aegletec.tagpaysocial.data.network.Resource
import com.aegletec.tagpaysocial.databinding.FragmentRegisterBinding
import com.aegletec.tagpaysocial.ui.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register.view.*
import kotlinx.coroutines.launch
import java.lang.Exception

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<AuthViewModel>()
    private lateinit var model_name:String
    private lateinit var imei_no:String
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var pin:String
    private lateinit var login_code:String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentRegisterBinding.bind(view)

        binding.submitBtn.setOnClickListener {
            with(binding){
                email=regEmail.reg_edit_email.text.toString().trim()
                password=regPassword.reg_edit_password.text.toString().trim()
                pin=regPin.edit_pin.text.toString().trim()
                login_code=logCode.edit_code.text.toString().trim()
                model_name= Build.MODEL
                imei_no=requestIMEI()
                //snackbar(requestIMEI()+"  "+model_name,view)
                validation(email,password,model_name,imei_no,pin,login_code,view)
            }
        }

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            with(binding){
                submitBtn.visible(false)
                progressBar.visible(it is Resource.Loading)
            }

            when(it){
                is Resource.Success->{
                    lifecycleScope.launch {
                        with(binding){
                            submitBtn.visible(true)
                            progressBar.visible(false)
                        }

                        viewModel.saveAccessTokens(it.value.token)

                        try {
                            //save to db
                            viewModel.saveDeviceToDb_viewModel(it.value)

                        }catch (e: Exception){
                            Log.i("LocalDbError",e.toString())

                        }

                        snackbar("User-Id: "+it.value.uuid,view)

                        val putData=RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                        putData.email=it.value.email
                        Navigation.findNavController(view).navigate(putData)
                    }
                }
                is Resource.Failure -> {
                    binding.submitBtn.visible(true)
                    binding.progressBar.visible(false)
                    handleApiError(it) {
                        validation(email,password,model_name,imei_no,pin,login_code,view)
                    }
                }
            }
        })

        binding.loginScreen.setOnClickListener {
            lifecycleScope.launch{
                //requireActivity().fromLoginActivity(AuthActivity::class.java,view)
                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }

    }

    private fun validation(email: String, password: String, model_name: String, imei_no: String,
                           pin: String, login_code: String, view: View) {

         if(login_code.isEmpty()){
            binding.logCode.error="Please enter code"
        }else if (email.isEmpty()){
            binding.regEmail.error="Please enter email"
        }else if(password.isEmpty()){
            binding.regPassword.error="Please enter password"
        }else if(pin.isEmpty()){
            binding.regPin.error="Please enter pin"
        }else if (model_name.isEmpty()){
            snackbar("Something went wrong",view)
            Log.i("Model name", "Null model name")
        }else if (imei_no.isEmpty()){
            snackbar("Something went wrong",view)
            Log.i("Android ID ", "Null android_id")
        }else{

            viewModel.register(RegisterDevice(login_code,email,imei_no,model_name,password,pin,true))

        }

    }



}