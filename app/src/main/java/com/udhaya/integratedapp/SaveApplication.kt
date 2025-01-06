package com.udhaya.integratedapp

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class SaveApplication : Application() {
    lateinit var realm: Realm
    override fun onCreate() {
        super.onCreate()
        initRealm()
    }

    fun initRealm() {
        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
            .name("UserDetailsRealm.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(configuration)
    }

}