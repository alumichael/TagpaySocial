package com.aegletec.tagpaysocial.data.api_models

import kotlinx.serialization.SerialName


data class LoginPost(
    @SerialName("emailOrPhone")
    val emailOrPhone: String,
    @SerialName("imei")
    val imei: String,
    @SerialName("model")
    val model: String,
    @SerialName("password")
    val password: String,
    @SerialName("rememberMe")
    val rememberMe: Boolean
)