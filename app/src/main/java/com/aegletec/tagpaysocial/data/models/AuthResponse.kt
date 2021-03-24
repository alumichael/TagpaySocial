package com.aegletec.tagpaysocial.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class AuthResponse(
    @SerialName("bvn")
    val bvn: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("email")
    val email: String,
    @SerialName("expiresIn")
    val expiresIn: Int,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("lastLoggedIn")
    val lastLoggedIn: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("level")
    val level: String,
    @SerialName("mobileNumber")
    val mobileNumber: String,
    @SerialName("permissions")
    val permissions: List<String>,
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("projectUuid")
    val projectUuid: String,
    @SerialName("roles")
    val roles: List<String>,
    @SerialName("token")
    val token: String,
    @SerialName("uuid")
    val uuid: String
)