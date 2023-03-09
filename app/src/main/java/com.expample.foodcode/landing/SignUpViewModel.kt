package com.example.foodcode
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    val firstName = mutableStateOf("")
    val lastName = mutableStateOf("")
    var email =  mutableStateOf("")
    var password =mutableStateOf("")
    var confirmPassword=  mutableStateOf("")
    fun isFirstNameValid(): Boolean {
        return firstName.value.isNotBlank()
    }

    fun isLastNameValid(): Boolean {
        return lastName.value.isNotBlank()
    }
    private fun isEmailValid(): Boolean {
        // validate email here, return true if valid, false otherwise
        return email.value.isNotBlank()
    }

    private fun isPasswordValid(): Boolean {
        // validate password here, return true if valid, false otherwise
        return password.value.isNotBlank()
    }

    private fun isConfirmPasswordValid(): Boolean {
        // validate confirm password here, return true if valid, false otherwise
        return confirmPassword.value.isNotBlank() && confirmPassword == password
    }
    fun isValid(): Boolean {
        return isFirstNameValid() && isLastNameValid()
    }
    fun isValided(): Boolean {
        return isEmailValid() && isPasswordValid() && isConfirmPasswordValid()
    }
    }
