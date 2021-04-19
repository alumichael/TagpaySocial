package com.aegletec.tagpaysocial.data.api_models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class UnsyncedBeneficiaries (
        @SerialName("uuid")
        val uuid: String,
        @SerialName("walletNumber")
        val walletNumber: String
    )
