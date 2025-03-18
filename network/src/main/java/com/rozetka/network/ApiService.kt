package com.rozetka.network

import com.rozetka.model.CoursesListModel
import com.rozetka.model.CoursesModel
import retrofit2.http.GET

interface ApiService {

    @GET("/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    suspend fun getCourses(): CoursesListModel


}