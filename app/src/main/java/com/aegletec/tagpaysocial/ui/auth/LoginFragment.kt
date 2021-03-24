package com.aegletec.tagpaysocial.ui.auth

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.aegletec.tagpaysocial.R
import com.aegletec.tagpaysocial.databinding.LoginFragmentBinding
import com.aegletec.tagpaysocial.ui.fromLoginActivity
import com.aegletec.tagpaysocial.ui.fromSplashActivity
import com.aegletec.tagpaysocial.ui.home.HomeActivity
import com.aegletec.tagpaysocial.ui.snackbar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.login_fragment.view.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.login_fragment) {

    private lateinit var binding: LoginFragmentBinding
    private lateinit var model_name:String
    private lateinit var imei_no:String

    private val viewModel by viewModels<AuthViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LoginFragmentBinding.bind(view)


        binding.submitBtn.setOnClickListener {
            with(binding){
                val email=logEmail.edit_email.text.toString().trim()
                val password=logPassword.edit_password.text.toString().trim()
                model_name=Build.MODEL
                snackbar(requestIMEI()+"  "+model_name,view)




            }




        }

        fun validation(){

        }

        binding.registerScreen.setOnClickListener {
          //  Navigation.findNavController(view).navigate(R.id.registerFragment)
            lifecycleScope.launch{
                //requireActivity().fromLoginActivity(AuthActivity::class.java,view)
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }

    }


    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestIMEI(): String {

        if (ContextCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.READ_PHONE_STATE) !=
                PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), android.Manifest.permission.READ_PHONE_STATE)) {

            } else {
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_PHONE_STATE), 2)

            }
        }

        try {

            val androidID = Settings.Secure.getString(requireActivity().contentResolver, Settings.Secure.ANDROID_ID)

            if (androidID != null) {
                 imei_no = androidID

            }
        } catch (ex: Exception) {
          //  view?.let { snackbar(ex.toString(), it) }
            Log.i("permissionError",ex.toString())

        }

        return imei_no
    }







}