package com.aegletec.tagpaysocial.data.api_models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class AssignedBeneficiaries() {

    var assignedBeneficiariesItem: ArrayList<AssignedBeneficiariesItem> = ArrayList()

    data class AssignedBeneficiariesItem(
        @SerialName("accountWalletNo")
        var accountWalletNo: String,
        @SerialName("active")
        var active: Boolean,
        @SerialName("age")
        var age: String,
        @SerialName("bvn")
        var bvn: String,
        @SerialName("community")
        var community: String,
        @SerialName("createdAt")
        var createdAt: String,
        @SerialName("deleted")
        var deleted: Boolean,
        @SerialName("enabled")
        var enabled: Boolean,
        @SerialName("enrollmentNo")
        var enrollmentNo: String,
        @SerialName("fieldId")
        var fieldId: String,
        @SerialName("fieldRev")
        var fieldRev: String,
        @SerialName("firstName")
        var firstName: String,
        @SerialName("gender")
        var gender: String,
        @SerialName("hhAdress")
        var hhAdress: String,
        @SerialName("hhhName")
        var hhhName: String,
        @SerialName("householdNo")
        var householdNo: String,
        @SerialName("id")
        var id: Int,
        @SerialName("idPrinted")
        var idPrinted: Boolean,
        @SerialName("lastName")
        var lastName: String,
        @SerialName("lga")
        var lga: String,
        @SerialName("nfcUid")
        var nfcUid: String,
        @SerialName("nin")
        var nin: String,
        @SerialName("picture")
        var picture: String,
        @SerialName("pspName")
        var pspName: String,
        @SerialName("pspType")
        var pspType: String,
        @SerialName("remark")
        var remark: String,
        @SerialName("role")
        var role: String,
        @SerialName("serviceNotified")
        var serviceNotified: Boolean,
        @SerialName("stateName")
        var stateName: String,
        @SerialName("synced")
        var synced: Boolean,
        @SerialName("type")
        var type: String,
        @SerialName("updatedAt")
        var updatedAt: String,
        @SerialName("uuid")
        var uuid: String,
        @SerialName("walletCreated")
        var walletCreated: Boolean,
        @SerialName("walletNumber")
        var walletNumber: String,
        @SerialName("ward")
        var ward: String,
        @SerialName("zone")
        var zone: String
    )
}