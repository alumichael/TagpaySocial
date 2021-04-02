package com.aegletec.tagpaysocial.data.localdb

import androidx.lifecycle.LiveData
import com.aegletec.tagpaysocial.data.api_models.AuthResponse
import com.aegletec.tagpaysocial.data.localdb.db_model.User
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmModel
import io.realm.RealmResults
import org.bson.types.ObjectId
import javax.inject.Inject


 class RealmOperation @Inject constructor(){

    private val realm = Realm.getDefaultInstance()

    val id=ObjectId()

     fun saveDevice(user: AuthResponse){
        realm.executeTransaction { realm: Realm ->
            val saveUser=realm.createObject(User::class.java, id)
            saveUser.bvn=user.bvn
            saveUser.createdAt=user.createdAt
            saveUser.email=user.email
            saveUser.expiresIn=user.expiresIn
            saveUser.firstName=user.firstName
            saveUser.lastLoggedIn=user.lastLoggedIn
            saveUser.lastName=user.lastName
            saveUser.level=user.level
            saveUser.mobileNumber=user.mobileNumber
           // userObject.permissions= user.permissions as RealmList<String>
            saveUser.phoneNumber=user.phoneNumber
            saveUser.projectUuid=user.projectUuid
          //  userObject.roles=user.roles as RealmList<String>
            saveUser.token=user.token
            saveUser.uuid=user.uuid

        }
    }

    fun deleteDb(){
        realm.executeTransaction { realm: Realm ->
            realm.deleteAll()
        }
    }


}