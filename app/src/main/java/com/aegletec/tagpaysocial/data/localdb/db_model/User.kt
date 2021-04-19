package com.aegletec.tagpaysocial.data.localdb.db_model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import kotlinx.serialization.SerialName
import org.bson.types.ObjectId

@RealmClass
open class User(
        @PrimaryKey
        var _id: ObjectId?=null,
        @SerialName("bvn")
        var bvn: String?=null,
        @SerialName("createdAt")
        var createdAt: String?=null,
        @SerialName("email")
        var email: String?=null,
        @SerialName("expiresIn")
        var expiresIn: Int = 0,
        @SerialName("firstName")
        var firstName: String?=null,
        @SerialName("lastLoggedIn")
        var lastLoggedIn: String?=null,
        @SerialName("lastName")
        var lastName: String?=null,
        @SerialName("level")
        var level: String?=null,
        @SerialName("mobileNumber")
        var mobileNumber: String?=null,
        @SerialName("permissions")
        var permissions: RealmList<String> = RealmList(),
        @SerialName("phoneNumber")
        var phoneNumber: String?=null,
        @SerialName("projectUuid")
        var projectUuid: String?=null,
        @SerialName("roles")
        var roles: RealmList<String> = RealmList(),
        @SerialName("token")
        var token: String?=null,
        @SerialName("uuid")
        var uuid: String?=null

        ):RealmObject()