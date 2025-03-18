package com.rozetka.network.di

import com.rozetka.network.ApiService
import com.rozetka.network.CourseRepository
import com.rozetka.network.provideOkHttpClient
import com.rozetka.network.provideRetrofit
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {

    single { provideOkHttpClient() }

    single { provideRetrofit(get()) }

    single { get<Retrofit>().create(ApiService::class.java) }

    single { CourseRepository(get()) }
}