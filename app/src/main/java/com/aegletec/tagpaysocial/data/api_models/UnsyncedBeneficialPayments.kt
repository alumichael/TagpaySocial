package com.aegletec.tagpaysocial.data.api_models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class UnsyncedBeneficialPayments (
        @SerialName("houseHoldNo")
        val houseHoldNo: String,
        @SerialName("uuid")
        val uuid: String
    )
