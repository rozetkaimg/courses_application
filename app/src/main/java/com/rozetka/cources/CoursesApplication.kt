package com.rozetka.cources

import android.app.Application
import com.rozetka.cources.di.appModule
import com.rozetka.domain.di.domainModule
import com.rozetka.network.di.networkModule
import com.rozetka.storage.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CoursesApplication(): Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@CoursesApplication)
            modules(
                listOf( domainModule,appModule, networkModule, databaseModule )
                )

        }

    }


}