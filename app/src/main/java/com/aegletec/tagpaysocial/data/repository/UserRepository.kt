package com.aegletec.tagpaysocial.data.repository


import com.aegletec.tagpaysocial.data.api_models.AuthResponse
import com.aegletec.tagpaysocial.data.localdb.RealmOperation
import com.aegletec.tagpaysocial.data.network.SafeRealmDbCall
import javax.inject.Inject

class UserRepository @Inject constructor(
        private val saverealm: RealmOperation
) :SafeRealmDbCall  {

    fun saveDeviceToDb_repo(user: AuthResponse) {
        //save the user data into db here
        saverealm.deleteDb()
        saverealm.saveDevice(user)
    }



}