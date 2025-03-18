package com.rozetka.domain.di

import com.rozetka.domain.GetCoursesUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetCoursesUseCase(get(), get()) }

}
