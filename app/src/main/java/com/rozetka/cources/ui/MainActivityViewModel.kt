package com.rozetka.cources.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rozetka.domain.GetCoursesUseCase
import kotlinx.coroutines.launch

class MainActivityViewModel(private val getCoursesUseCase: GetCoursesUseCase) : ViewModel() {

    private val _isDatabaseEmpty = MutableLiveData<Boolean>()
    val isDatabaseEmpty: LiveData<Boolean> = _isDatabaseEmpty

    init {
        checkDatabaseEmpty()
    }

    private fun checkDatabaseEmpty() {
        viewModelScope.launch {

            val isEmpty = getCoursesUseCase.getDaoInfo()
            _isDatabaseEmpty.value = isEmpty
        }
    }
}