package com.rozetka.cources.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rozetka.domain.GetCoursesUseCase
import com.rozetka.model.CoursesModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val getCoursesUseCase: GetCoursesUseCase) : ViewModel() {

    private val _courses = MutableStateFlow<List<CoursesModel>>(emptyList())
    val courses: StateFlow<List<CoursesModel>> get() = _courses

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    init {
        loadCourses()
    }
    fun hasLikeCourse(courseId: Int, hasLike: Any?){
        viewModelScope.launch {
           getCoursesUseCase.setCourseSetLike(courseId, hasLike as Boolean)

        }

    }

    private fun loadCourses() {
        getCoursesUseCase.getCourses()
            .catch { e ->

                _error.value = e.localizedMessage
            }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
            .onEach { courses ->
                _courses.value = courses
                println(courses)
            }
            .launchIn(viewModelScope)
    }
}
