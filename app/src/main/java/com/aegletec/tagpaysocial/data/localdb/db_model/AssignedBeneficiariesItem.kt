package com.aegletec.tagpaysocial.data.localdb.db_model

import io.realm.RealmObject
import io.realm.annotations.RealmClass
import kotlinx.serialization.SerialName

@RealmClass
open class AssignedBeneficiariesItem(
        @SerialName("accountWalletNo")
        var accountWalletNo: String?=null,
        @SerialName("active")
        var active: Boolean?=null,
        @SerialName("age")
        var age: String?=null,
        @SerialName("bvn")
        var bvn: String?=null,
        @SerialName("community")
        var community: String?=null,
        @SerialName("createdAt")
        var createdAt: String?=null,
        @SerialName("deleted")
        var deleted: Boolean?=null,
        @SerialName("enabled")
        var enabled: Boolean?=null,
        @SerialName("enrollmentNo")
        var enrollmentNo: String?=null,
        @SerialName("fieldId")
        var fieldId: String?=null,
        @SerialName("fieldRev")
        var fieldRev: String?=null,
        @SerialName("firstName")
        var firstName: String?=null,
        @SerialName("gender")
        var gender: String?=null,
        @SerialName("hhAdress")
        var hhAdress: String?=null,
        @SerialName("hhhName")
        var hhhName: String?=null,
        @SerialName("householdNo")
        var householdNo: String?=null,
        @SerialName("id")
        var id: Int?=0,
        @SerialName("idPrinted")
        var idPrinted: Boolean?=null,
        @SerialName("lastName")
        var lastName: String?=null,
        @SerialName("lga")
        var lga: String?=null,
        @SerialName("nfcUid")
        var nfcUid: String?=null,
        @SerialName("nin")
        var nin: String?=null,
        @SerialName("picture")
        var picture: String?=null,
        @SerialName("pspName")
        var pspName: String?=null,
        @SerialName("pspType")
        var pspType: String?=null,
        @SerialName("remark")
        var remark: String?=null,
        @SerialName("role")
        var role: String?=null,
        @SerialName("serviceNotified")
        var serviceNotified: Boolean?=null,
        @SerialName("stateName")
        var stateName: String?=null,
        @SerialName("synced")
        var synced: Boolean?=null,
        @SerialName("type")
        var type: String?=null,
        @SerialName("updatedAt")
        var updatedAt: String?=null,
        @SerialName("uuid")
        var uuid: String?=null,
        @SerialName("walletCreated")
        var walletCreated: Boolean?=null,
        @SerialName("walletNumber")
        var walletNumber: String?=null,
        @SerialName("ward")
        var ward: String?=null,
        @SerialName("zone")
        var zone: String?=null
): RealmObject()