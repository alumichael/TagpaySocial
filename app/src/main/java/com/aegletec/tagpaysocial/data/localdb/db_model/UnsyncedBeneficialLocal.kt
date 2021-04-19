package com.aegletec.tagpaysocial.data.localdb.db_model


import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@RealmClass
open class UnsyncedBeneficialLocal(
            @PrimaryKey
            var _id: ObjectId? = null,
            @SerialName("uuid")
            var uuid: String?=null,
            @SerialName("walletNumber")
            var walletNumber: String?=null
): RealmObject()



