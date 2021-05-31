package com.aegletec.tagpaysocial

import androidx.datastore.preferences.core.preferencesKey

open class Constant {

    companion object{
        val BASE_URL = "http://206.189.161.140:8080/"
        val USERPREF="user_pref"


        val ACCESS_TOKEN = preferencesKey<String>("key_access_token")
        val TOTAL_ASSIGN_BENEFICIALS = preferencesKey<Int>("total_assign_beneficials")
        val TOTAL_ASSIGN_COMMUNITIES = preferencesKey<Int>("total_assign_communities")
        val TOTAL_ASSIGN_BENEFICIAL_PAYMENT = preferencesKey<Int>("total_assign_beneficial_payment")
        val TOTAL_UPDATED_BENEFICIALS = preferencesKey<Int>("total_updated_beneficials")
        val TOTAL_UPDATED_BENEFICIALS_PAYMENT = preferencesKey<Int>("total_updated_beneficials_payment")
        val TOTAL_UPDATED_COMMUNITIES = preferencesKey<Int>("total_updated_communities")
        val LOGGED_IN = preferencesKey<Boolean>("logged_in")
        val UUID = preferencesKey<String>("uuid")
        val PROJECT_UUID = preferencesKey<String>("project_uuid")
        val DEVICE_ID = preferencesKey<String>("device_id")

    }
}