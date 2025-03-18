package com.rozetka.domain

import com.rozetka.domain.ext.toEntityList
import com.rozetka.domain.ext.toModelList
import com.rozetka.model.CoursesModel
import com.rozetka.network.CourseRepository
import com.rozetka.storage.CoursesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import android.util.Log

import kotlinx.coroutines.flow.flow

class GetCoursesUseCase(
    private val courseRepository: CourseRepository,
    private val coursesDao: CoursesDao
) {

    fun getCourses(): Flow<List<CoursesModel>> = flow {
        val localCoursesEntity = coursesDao.getAllCourses().firstOrNull() ?: emptyList()
        val localCourses = localCoursesEntity.toModelList()

        Log.d("GetCoursesUseCase", "Local courses count: ${localCourses.size}")

        if (localCourses.isEmpty()) {
            Log.d("GetCoursesUseCase", "Local database is empty, fetching remote courses")
            try {
                val remoteCourses = courseRepository.fetchCourses()
                coursesDao.insertAll(remoteCourses.courses.toEntityList())
                Log.d(
                    "GetCoursesUseCase",
                    "Remote courses fetched and inserted: ${remoteCourses.courses.size}"
                )
                emit(remoteCourses.courses)
            } catch (e: Exception) {
                Log.e(
                    "GetCoursesUseCase",
                    "Error fetching remote courses: ${e.localizedMessage}",
                    e
                )
                emit(emptyList())
            }
        } else {
            Log.d("GetCoursesUseCase", "Local courses found, emitting local data")
            emit(localCourses)
        }
    }

    suspend fun getDaoInfo(): Boolean  =  coursesDao.isEmpty()

    suspend fun setCourseSetLike(courseId: Int, likeStatus: Boolean) = coursesDao.updateCourse(courseId, likeStatus)


}