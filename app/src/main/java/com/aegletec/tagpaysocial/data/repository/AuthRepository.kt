package com.aegletec.tagpaysocial.data.repository

import com.aegletec.tagpaysocial.data.Userpreference
import com.aegletec.tagpaysocial.data.models.AuthResponse
import com.aegletec.tagpaysocial.data.models.LoginPost
import com.aegletec.tagpaysocial.data.models.RegisterDevice
import com.aegletec.tagpaysocial.data.network.ApiInterface
import com.aegletec.tagpaysocial.data.network.Resource
import com.aegletec.tagpaysocial.data.network.SafeApiCall
import kotlinx.coroutines.flow.first
import kotlinx.serialization.SerialName
import javax.inject.Inject


class AuthRepository @Inject constructor(
        private  val api: ApiInterface,
        private val userpreference: Userpreference
        ):SafeApiCall {
    
       suspend fun login(
              loginPost: LoginPost
       ) =safeApiCall{

           api.login(loginPost)

       }

    suspend fun register_device(
            registerDevice: RegisterDevice
    )=safeApiCall{
        api.registerDevice(registerDevice)

    }

    suspend fun saveAccessTokens(accessToken: String) {
        userpreference.saveAccessTokens(accessToken)
    }




}