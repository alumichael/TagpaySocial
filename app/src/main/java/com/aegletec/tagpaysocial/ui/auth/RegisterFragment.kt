package com.aegletec.tagpaysocial.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.aegletec.tagpaysocial.R
import com.aegletec.tagpaysocial.databinding.FragmentRegisterBinding
import com.aegletec.tagpaysocial.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<AuthViewModel>()




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentRegisterBinding.bind(view)

        binding.submitBtn.setOnClickListener {  }

        binding.loginScreen.setOnClickListener {
            lifecycleScope.launch{
                //requireActivity().fromLoginActivity(AuthActivity::class.java,view)
                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }

    }


}