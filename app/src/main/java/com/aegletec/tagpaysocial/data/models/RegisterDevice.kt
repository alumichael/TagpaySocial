package com.aegletec.tagpaysocial.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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