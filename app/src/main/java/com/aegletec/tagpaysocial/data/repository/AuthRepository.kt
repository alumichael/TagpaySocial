package com.aegletec.tagpaysocial.data.repository

import com.aegletec.tagpaysocial.data.Userpreference
import com.aegletec.tagpaysocial.data.api_models.LoginPost
import com.aegletec.tagpaysocial.data.api_models.RegisterDevice
import com.aegletec.tagpaysocial.data.network.ApiInterface
import com.aegletec.tagpaysocial.data.network.SafeApiCall
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