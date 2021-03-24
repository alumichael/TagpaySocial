package com.aegletec.tagpaysocial.ui.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aegletec.tagpaysocial.data.models.AuthResponse
import com.aegletec.tagpaysocial.data.models.LoginPost
import com.aegletec.tagpaysocial.data.models.RegisterDevice
import com.aegletec.tagpaysocial.data.network.Resource
import com.aegletec.tagpaysocial.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel @ViewModelInject constructor(
        private val repository: AuthRepository)
    : ViewModel() {
    private val _loginResponse: MutableLiveData<Resource<AuthResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<AuthResponse>> get() = _loginResponse

    fun login(
            loginPost: LoginPost
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(loginPost)
    }


    fun register(
            registerDevice: RegisterDevice
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.register_device(registerDevice)
    }




}