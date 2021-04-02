package com.aegletec.tagpaysocial.data.api_models


import kotlinx.serialization.SerialName

data class RegisterDevice(
    @SerialName("code")
    val code: String,
    @SerialName("emailOrPhone")
    val emailOrPhone: String,
    @SerialName("imei")
    val imei: String,
    @SerialName("model")
    val model: String,
    @SerialName("password")
    val password: String,
    @SerialName("pin")
    val pin: String,
    @SerialName("rememberMe")
    val rememberMe: Boolean
)