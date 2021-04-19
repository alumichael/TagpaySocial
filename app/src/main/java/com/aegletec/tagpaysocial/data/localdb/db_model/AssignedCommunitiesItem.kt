package com.aegletec.tagpaysocial.data.localdb.db_model

import io.realm.RealmObject
import io.realm.annotations.RealmClass
import kotlinx.serialization.SerialName

@RealmClass
open class AssignedCommunitiesItem(
        @SerialName("beneficiaryCount")
        var beneficiaryCount: Int?=0,
        @SerialName("community")
        var community: String?=null,
        @SerialName("id")
        var id: Int?=0,
        @SerialName("lga")
        var lga: String?=null,
        @SerialName("project")
        var project: String?=null,
        @SerialName("state")
        var state: String?=null,
        @SerialName("syncedBeneficiaryCount")
        var syncedBeneficiaryCount: Int?=0,
        var user: String?=null,
        @SerialName("uuid")
        var uuid: String?=null,
        @SerialName("ward")
        var ward: String?=null
):RealmObject()