package com.aegletec.tagpaysocial

import androidx.datastore.preferences.core.preferencesKey

open class Constant {

    companion object{
         val ACCESS_TOKEN = preferencesKey<String>("key_access_token")
         val USERPREF="user_data_store"
    }
}