package com.aegletec.tagpaysocial.ui.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aegletec.tagpaysocial.data.api_models.AuthResponse
import com.aegletec.tagpaysocial.data.api_models.LoginPost
import com.aegletec.tagpaysocial.data.api_models.RegisterDevice
import com.aegletec.tagpaysocial.data.localdb.db_model.User
import com.aegletec.tagpaysocial.data.network.Resource
import com.aegletec.tagpaysocial.data.repository.AuthRepository
import com.aegletec.tagpaysocial.data.repository.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel @ViewModelInject constructor(
        private val auth_repository: AuthRepository,
        private val user_repository: UserRepository)
    : ViewModel() {
    private val _loginResponse: MutableLiveData<Resource<AuthResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<AuthResponse>> get() = _loginResponse

    fun login(
            loginPost: LoginPost
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = auth_repository.login(loginPost)
    }


    fun register(
            registerDevice: RegisterDevice
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = auth_repository.register_device(registerDevice)
    }

    suspend fun saveAccessTokens(accessToken: String) {
        auth_repository.saveAccessTokens(accessToken)
    }

    fun saveDeviceToDb_viewModel(user: AuthResponse){
        user_repository.saveDeviceToDb_repo(user)
    }



}