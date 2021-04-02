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
import com.aegletec.tagpaysocial.Constant.Companion.USERPREF
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Userpreference @Inject constructor(context: Context){

    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences> = applicationContext.createDataStore(
            name = USERPREF
    )

    val accessToken: Flow<String?>
        get() = dataStore.data.map { preferences -> preferences[ACCESS_TOKEN]
        }

    suspend fun saveAccessTokens(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken

        }
    }
    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }



}