package com.aegletec.tagpaysocial.data.network

import com.aegletec.tagpaysocial.data.models.AuthResponse
import com.aegletec.tagpaysocial.data.models.LoginPost
import com.aegletec.tagpaysocial.data.models.RegisterDevice
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("api/v1/auth/register-device")
    suspend fun registerDevice(
           @Body registerDevice: RegisterDevice
    ):AuthResponse

    @POST("api/v1/auth/social")
    suspend fun login(
            @Body loginPost: LoginPost
    ):AuthResponse


}

