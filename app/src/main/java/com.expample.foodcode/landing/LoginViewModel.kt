package com.example.foodcode

import Api
import LoginRequest
import LoginResponse
//import LoginResponseEntity
//import MyDatabase

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*

import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//class LoginViewModel : ViewModel() {
//
////    val userList = arrayListOf<LoginResponse>()
//    var email by mutableStateOf("")
//
//    var password by mutableStateOf("")
//
//    val isEmailValid: Boolean
//        get() = email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
//
//    val isPasswordValid: Boolean
//        get() = password.isNotEmpty() && password.length >= 6
//
//    fun onEmailChange(newValue: String) {
//        email = newValue
//    }
//
//    fun onPasswordChange(newValue: String) {
//        password = newValue
//    }
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("https://api.perfectportions.greatergoods.com/v1/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//
//    private val _userList = MutableLiveData<List<LoginResponse>>()
//    val userList: LiveData<List<LoginResponse>> = _userList
//    private val api = retrofit.create(Api::class.java)
//
//    private val _token = MutableLiveData<String>()
//    val token: LiveData<String> = _token
//
//    fun login(email: String, password: String) {
//
//
//        viewModelScope.launch {
//
//            val request = LoginRequest(email, password)
//            val response = api.login(request)
//           println(response)
//            if (response.isSuccessful) {
//                val token = response.body()?.token
//                val lastName = response.body()?.lastName
//                _token.postValue(token)
//                val userResponse = response.body()
//                val user = LoginResponse(
//                    dob = userResponse?.dob,
//                    email = userResponse?.email ?: "",
//                    firstName = userResponse?.firstName ?: "",
//                    gender = userResponse?.gender,
//                    id = userResponse?.id ?: 0,
//                    lastName = userResponse?.lastName ?: "",
//                    scale = userResponse?.scale ?: "",
//                    token = userResponse?.token ?: "",
//                    zipcode = userResponse?.zipcode
//                )
//             // Update user list
//                val userList = arrayListOf(user)
//                _userList.postValue(userList)
//
//                // Save user list to SharedPreferences
//
//
//                println(token)
//                println(lastName)
//
//            } else {
//                // Handle failed login
//                println("F kaviya")
//            }
//        }
//    }
//
//
//}

class LoginViewModel() : ViewModel() {

    //    val userList = arrayListOf<LoginResponse>()
    var email by mutableStateOf("")

    var password by mutableStateOf("")

    val isEmailValid: Boolean
        get() = email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    val isPasswordValid: Boolean
        get() = password.isNotEmpty() && password.length >= 6

    fun onEmailChange(newValue: String) {
        email = newValue
    }

    fun onPasswordChange(newValue: String) {
        password = newValue
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.perfectportions.greatergoods.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val _userList = MutableLiveData<List<LoginResponse>>()
    val userList: LiveData<List<LoginResponse>> = _userList
    private val api = retrofit.create(Api::class.java)

    private val _token = MutableLiveData<String>()
    val token: LiveData<String> = _token

    fun login(email: String, password: String) {


        viewModelScope.launch {

            val request = LoginRequest(email, password)
            val response = api.login(request)
            println(response)
            if (response.isSuccessful) {
                val token = response.body()?.token
                val lastName = response.body()?.lastName
                _token.postValue(token)
                val userResponse = response.body()
                val user = LoginResponse(
                    dob = userResponse?.dob,
                    email = userResponse?.email ?: "",
                    firstName = userResponse?.firstName ?: "",
                    gender = userResponse?.gender,
                    id = userResponse?.id ?: 0,
                    lastName = userResponse?.lastName ?: "",
                    scale = userResponse?.scale ?: "",
                    token = userResponse?.token ?: "",
                    zipcode = userResponse?.zipcode
                )
                // Update user list
                val userList = listOf(user)
                _userList.postValue(userList)
                // Save user list to SharedPreferences
                // Save user list to SharedPreferences
//                savePeople(context = context, User = userList)

                _token.postValue(token)

                println(token)
                println(lastName)

            } else {
                // Handle failed login
                println("F kaviya")
            }
        }
    }


}


