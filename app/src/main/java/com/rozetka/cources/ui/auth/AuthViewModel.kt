package com.rozetka.cources.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel: ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _isLoginButtonEnabled = MutableLiveData<Boolean>(false)
    val isLoginButtonEnabled: LiveData<Boolean> get() = _isLoginButtonEnabled

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
        validateForm()
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
        validateForm()
    }

    private fun validateForm() {
        val isEmailValid = _email.value?.let { it.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)$")) } ?: false
        val isPasswordValid = !(_password.value.isNullOrEmpty())
        _isLoginButtonEnabled.value = isEmailValid && isPasswordValid
    }

    fun onLoginClicked() {

    }
}