package com.aegletec.tagpaysocial.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.clear
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.aegletec.tagpaysocial.Constant
import com.aegletec.tagpaysocial.Constant.Companion.ACCESS_TOKEN
import com.aegletec.tagpaysocial.Constant.Companion.DEVICE_ID
import com.aegletec.tagpaysocial.Constant.Companion.LOGGED_IN
import com.aegletec.tagpaysocial.Constant.Companion.PROJECT_UUID
import com.aegletec.tagpaysocial.Constant.Companion.TOTAL_ASSIGN_BENEFICIALS
import com.aegletec.tagpaysocial.Constant.Companion.TOTAL_ASSIGN_BENEFICIAL_PAYMENT
import com.aegletec.tagpaysocial.Constant.Companion.TOTAL_ASSIGN_COMMUNITIES
import com.aegletec.tagpaysocial.Constant.Companion.TOTAL_UPDATED_BENEFICIALS
import com.aegletec.tagpaysocial.Constant.Companion.TOTAL_UPDATED_BENEFICIALS_PAYMENT
import com.aegletec.tagpaysocial.Constant.Companion.TOTAL_UPDATED_COMMUNITIES
import com.aegletec.tagpaysocial.Constant.Companion.USERPREF
import com.aegletec.tagpaysocial.Constant.Companion.UUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class Userpreference @Inject constructor( context: Context){

    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences> = applicationContext.createDataStore(
            name = USERPREF
    )

    val accessToken= dataStore.data.map { preferences -> preferences[ACCESS_TOKEN]
        }


    suspend fun setLoggedin(updated_beneficials: Boolean) {
        dataStore.edit { preferences ->
            preferences[LOGGED_IN] = updated_beneficials

        }
    }
    fun isLoggedIn()= dataStore.data.map { preferences -> preferences[LOGGED_IN]?:false}


    suspend fun saveAccessTokens(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken

        }
    }
    fun accessToken() = dataStore.data.map {preferences -> preferences[ACCESS_TOKEN]  }

    //total assign beneficials

    suspend fun saveTotalAssignBeneficials(total_beneficials: Int) {
        dataStore.edit { preferences ->
            preferences[TOTAL_ASSIGN_BENEFICIALS] = total_beneficials

        }
    }
    fun totalBeneficials() = dataStore.data.map { preferences -> preferences[TOTAL_ASSIGN_BENEFICIALS]?:0 }


    suspend fun saveTotalAssignCommnities(total_communities: Int) {
        dataStore.edit { preferences ->
            preferences[TOTAL_ASSIGN_COMMUNITIES] = total_communities

        }
    }
    fun totalCommunities() = dataStore.data.map { preferences -> preferences[TOTAL_ASSIGN_COMMUNITIES]?:0 }

    suspend fun saveTotalAssignBeneficialPayment(total_beneficial_payment: Int) {
        dataStore.edit { preferences ->
            preferences[TOTAL_ASSIGN_BENEFICIAL_PAYMENT] = total_beneficial_payment

        }
    }
    fun totalBeneficialPayment() = dataStore.data.map { preferences -> preferences[TOTAL_ASSIGN_BENEFICIAL_PAYMENT]?:0 }




    //updated beneficials

    suspend fun saveUpdatedAssignBeneficials(updated_beneficials: Int) {
        dataStore.edit { preferences ->
            preferences[TOTAL_UPDATED_BENEFICIALS] = updated_beneficials

        }
    }
    fun updatedBeneficials()= dataStore.data.map { preferences -> preferences[TOTAL_UPDATED_BENEFICIALS]?:0 }


    suspend fun saveUpdatedAssignCommunities(updated_beneficials: Int) {
        dataStore.edit { preferences ->
            preferences[TOTAL_UPDATED_COMMUNITIES] = updated_beneficials

        }
    }
    fun updatedCommunities()= dataStore.data.map { preferences -> preferences[TOTAL_UPDATED_COMMUNITIES]?:0 }

    suspend fun saveUpdatedAssignBeneficalPayement(updated_beneficials: Int) {
        dataStore.edit { preferences ->
            preferences[TOTAL_UPDATED_BENEFICIALS_PAYMENT] = updated_beneficials

        }
    }
    fun updatedAssignbeneficialPayment()= dataStore.data.map { preferences -> preferences[TOTAL_UPDATED_BENEFICIALS_PAYMENT]?:0 }


    //uuids
    suspend fun saveUUID(uuid: String) {
        dataStore.edit { preferences ->
            preferences[UUID] = uuid

        }
    }
    fun uuid() = dataStore.data.map { preferences -> preferences[UUID]?:"" }


    suspend fun saveProjectUUID(project_uuid: String) {
        dataStore.edit { preferences ->
            preferences[PROJECT_UUID] = project_uuid

        }
    }
    fun project_uuid() = dataStore.data.map { preferences -> preferences[PROJECT_UUID]?:""}

    suspend fun saveDeviceID(device_id: String) {
        dataStore.edit { preferences ->
            preferences[DEVICE_ID] = device_id

        }
    }
    fun device_id() = dataStore.data.map { preferences -> preferences[DEVICE_ID]?:"" }

    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }


}