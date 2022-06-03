package com.example.financialapplication

import androidx.room.Room
import co.zsmb.rainbowcake.dagger.RainbowCakeApplication
import co.zsmb.rainbowcake.dagger.RainbowCakeComponent
import com.example.financialapplication.data.cache.AppDatabase
import com.example.financialapplication.di.DaggerAppComponent

class ItemsApplication : RainbowCakeApplication() {

    override lateinit var injector: RainbowCakeComponent

    companion object {
        lateinit var db: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "items.db"
        ).build()
    }

    override fun setupInjector() {
        injector = DaggerAppComponent.create()
    }
}