package com.example.ecobank

import android.app.Application
import com.example.ecobank.di.AppContainer
import com.example.ecobank.di.DefaultAppContainer

class EcoBankApp : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
