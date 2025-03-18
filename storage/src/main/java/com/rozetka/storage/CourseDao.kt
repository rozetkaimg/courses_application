package com.rozetka.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.rozetka.model.CoursesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CoursesDao {
    @Insert
    suspend fun insert(course: CourseEntity)

    @Transaction
    @Insert
    suspend fun insertAll(course: List<CourseEntity>)

    @Query("SELECT * FROM courses")
    fun getAllCourses(): Flow<List<CourseEntity>>

    @Query("SELECT COUNT(*) = 0 FROM courses")
    suspend fun isEmpty(): Boolean

    @Update
    suspend fun update(course: CourseEntity)

    @Query("UPDATE courses SET hasLike = :hasLike WHERE id = :id")
    suspend fun updateCourse(id: Int, hasLike: Boolean)
}