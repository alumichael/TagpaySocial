package com.aegletec.tagpaysocial.data.localdb.db_model


import io.realm.RealmObject
import io.realm.annotations.RealmClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@RealmClass
open class AssignedBeneficialPaymentsItem(
    @SerialName("amount")
    var amount: Int? = 0,
    @SerialName("houseHoldNo")
    var houseHoldNo: String? = null,
    @SerialName("id")
    var id: Int? = 0,
    @SerialName("pspCode")
    var pspCode: String? = null,
    @SerialName("scheduleCode")
    var scheduleCode: String? = null,
    @SerialName("socialPaymentBatchNctoUuid")
    var socialPaymentBatchNctoUuid: String? = null,
    @SerialName("uuid")
    var uuid: String? = null
):RealmObject()