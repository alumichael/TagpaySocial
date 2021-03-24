package com.aegletec.tagpaysocial.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


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