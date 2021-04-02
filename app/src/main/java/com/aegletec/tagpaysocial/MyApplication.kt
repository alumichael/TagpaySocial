package com.aegletec.tagpaysocial

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import io.realm.RealmConfiguration

@HiltAndroidApp
class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val realmName = "Tap Social"
        val config = RealmConfiguration.Builder().name(realmName)
                .deleteRealmIfMigrationNeeded()
                .allowWritesOnUiThread(true)
                .build()
        Realm.setDefaultConfiguration(config)
    }
}