package com.rozetka.network

import com.rozetka.model.CoursesListModel

class CourseRepository(private val apiService: ApiService) {

    suspend fun fetchCourses(): CoursesListModel {
        return apiService.getCourses()
    }



}