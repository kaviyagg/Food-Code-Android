package com.example.foodcode

import LoginResponse
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foodcode.ui.theme.LightGreen
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import savePeople


@Composable
fun Login(navController: NavHostController) {
    val loginViewModel = viewModel<LoginViewModel>()
    val context = LocalContext.current
    val userList = remember { mutableStateOf<List<LoginResponse>>(emptyList()) }
    val lifecycleOwner = LocalLifecycleOwner.current
    val userListObserver = remember { Observer<List<LoginResponse>> { userList.value = it } }

    DisposableEffect(loginViewModel) {
        loginViewModel.userList.observe(lifecycleOwner, userListObserver)
        onDispose {
            loginViewModel.userList.removeObserver(userListObserver)
        }
    }
    val token = remember { mutableStateOf<String?>(null) }
    val tokenObserver = remember { Observer<String?> { token.value = it } }

    // Observe the token Flow when the composable is first launched
    DisposableEffect(loginViewModel) {
        loginViewModel.token.observe(lifecycleOwner, tokenObserver)
        onDispose {
            // Stop observing the token Flow when the composable is no longer active
            loginViewModel.token.removeObserver(tokenObserver)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = LightGreen

            ) {
                Text("Login",
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.CenterHorizontally),
                             fontWeight = FontWeight.Bold,
                            )
            }
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = LightGreen
            ) {
Row(verticalAlignment = Alignment.CenterVertically){
    BottomNavigationItem(
        selected = true,
        onClick = {
            navController.navigate(Screen.Landing.route)
        },
        icon = {
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "ArrowBack Icon"
                )
                Text(
                    text = "BACK",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 4.dp)
                )}

        },

        )
    BottomNavigationItem(
        selected = true,
        onClick = {
            val user = userList.value
            loginViewModel.login(loginViewModel.email,loginViewModel.password)
            savePeople(context, user)
            if (token.value != null) {
                navController.navigate(Screen.MainScreen.route)
            }
        },
        icon = {
            Row(verticalAlignment = Alignment.CenterVertically){

                Text(
                    text ="LOG IN",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 4.dp)
                )
                Icon(
                    imageVector = Icons.Rounded.ArrowForward,
                    contentDescription = "ArrowForward Icon"
                )}
        },

        )
}
            }
        },
        content = {


//            LoginScreen(loginViewModel = loginViewModel,navController)
            LoginScreen(loginViewModel = loginViewModel)

        },
    )
}




//@Composable
//fun LoginPage(loginViewModel: LoginViewModel, navController: NavHostController) {
//    var showDialog by remember { mutableStateOf(false) }
//    val emailState = remember { mutableStateOf(TextFieldValue()) }
//    val passwordState = remember { mutableStateOf(TextFieldValue()) }
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(20.dp),
//        shape = RoundedCornerShape(8.dp),
//        elevation = 4.dp
//    ) {
//        Column(
//            modifier = Modifier.padding(20.dp)
//
//        ) {
//            Spacer(modifier = Modifier.height(30.dp))
//            TextField(
//                value = emailState.value,
//                onValueChange = { emailState.value = it },
//                label = { Text(text = "Email") },
//                modifier = Modifier.fillMaxWidth()
//                    .padding(10.dp),
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Email
//                ),
//            )
////            if(!loginViewModel.isEmailValid){
////                Text(text= "Must use a valid email",
////                    color = Color.Red,
////                    modifier = Modifier.padding(top = 4.dp, start = 10.dp))
////            }
//            Spacer(modifier = Modifier.height(30.dp))
//            TextField(
//                value = passwordState.value,
//                onValueChange = { passwordState.value = it },
//                label = { Text(text = "Password") },
//                modifier = Modifier.fillMaxWidth()
//                    .padding(10.dp),
//                visualTransformation = PasswordVisualTransformation(),
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Password
//                ),
//            )
////            if(!loginViewModel.isPasswordValid){
////                Text(text= "Must use a valid password",
////                    color = Color.Red,
////                    modifier = Modifier.padding(top = 4.dp, start = 10.dp))
////            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(onClick = {
//                showDialog = true
//            },contentPadding = PaddingValues(0.dp),
//                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
//                elevation = ButtonDefaults.elevation(0.dp)) {
//                Text(text = "Forgot your password?", color = LightGreen)
//            }
//
//            Button(
//                onClick = { loginViewModel.login(emailState.value.text, passwordState.value.text) },
//                modifier = Modifier.padding(top = 16.dp)
//            ) {
//                Text(text = "Login")
//            }
//        }
////        val loginResult by loginViewModel.loginResult.observeAsState()
////        loginResult?.let { result ->
////            when (result) {
////                is Result.Success -> {
////                    // Handle success case
////                    val loginResponse = result.data
////                    // Do something with loginResponse
////                }
////                is Result.Failure -> {
////                    // Handle failure case
////                    val exception = result.exception
////                    // Do something with exception
////                }
////            }
////        }
//        if (showDialog) {
//            AlertDialog(
//                onDismissRequest = { showDialog = false },
//                title = { Text(text = "Password Reset", modifier = Modifier.padding(2.dp))
//                       },
//                text = {
//                    Spacer(modifier = Modifier.height(16.dp))
//                    TextField(
//                        value = "Enter your email below",
//                        onValueChange = { },
//                        label = { Text(text = "Email") },
//                        modifier = Modifier.fillMaxWidth(),
//                        keyboardOptions = KeyboardOptions(
//                            keyboardType = KeyboardType.Email
//                        )
//                    )
//                },
//                confirmButton = {
//                    Button(
//                        onClick = { showDialog = false }
//                    ) {
//                        Text(text = "Reset Password")
//                    }
//                },
//                dismissButton = {
//                    Button(
//                        onClick = { showDialog = false }
//                    ) {
//                        Text(text = "Cancel")
//                    }
//                }
//            )
//        }
//    }
//}
//@Composable
//fun LoginScreen(loginViewModel: LoginViewModel, navController: NavHostController) {
//    val email = remember { mutableStateOf("") }
//    val password = remember { mutableStateOf("") }
//
//    val token = remember { mutableStateOf<String?>(null) }
//
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val tokenObserver = remember { Observer<String?> { token.value = it } }
//
//    // Observe the token Flow when the composable is first launched
//    DisposableEffect(loginViewModel) {
//        loginViewModel.token.observe(lifecycleOwner, tokenObserver)
//        onDispose {
//            // Stop observing the token Flow when the composable is no longer active
//            loginViewModel.token.removeObserver(tokenObserver)
//        }
//    }
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = "LOGIN",
//            modifier = Modifier.padding(30.dp)
//                .background(LightGreen)
//                .fillMaxWidth(),
//            fontSize = 24.sp,
//        )
//// Login form
//
//        Column(
//                modifier = Modifier.weight(1f),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                OutlinedTextField(
//                    value = email.value,
//                    onValueChange = { email.value = it },
//                    label = { Text("Email") }
//                )
//
//                OutlinedTextField(
//                    value = password.value,
//                    onValueChange = { password.value = it },
//                    label = { Text("Password") },
//                    visualTransformation = PasswordVisualTransformation()
//                )
//
//                Spacer(Modifier.height(16.dp))
//
//
//            }
//
//
//        Spacer(Modifier.height(16.dp))
//        Button(
//            onClick = { loginViewModel.login(email.value, password.value) }
//        ) {
//            Text("Login")
//        }
//        if (token.value != null) {
//            // Navigate to the next screen or show a success message
//
//            navController.navigate(Screen.MainScreen.route)
//        }
//    }
//}
//




@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {

    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.isNotEmpty() && password.length >= 6
    }
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
            elevation = 20.dp
    ) {
        Column(
            modifier = Modifier
                   .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Spacer(modifier = Modifier.height(18.dp))
                TextField(

                    value = loginViewModel.email,
                    onValueChange = {
                        loginViewModel.email = it
                    },
                    label = { Text("Email") },
                    isError = !isValidEmail(loginViewModel.email) || loginViewModel.email.isEmpty(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    ),
                )}
//            Spacer(modifier = Modifier.height(16.dp))
                if (!isValidEmail(loginViewModel.email) && !loginViewModel.email.isEmpty()) {
                    Text(
                        text = "Please enter a valid email address",
                        color = Color.Red,
//                     ipo

                    )
                }
            Spacer(Modifier.height(30.dp))
                TextField(
                    value = loginViewModel.password,
                    onValueChange = { loginViewModel.password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = !isValidPassword(loginViewModel.password) || loginViewModel.password.isEmpty(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password
                    )
                )
                if (!isValidEmail(loginViewModel.password) && !loginViewModel.password.isEmpty()) {
                    Text(
                        text = "Please enter a valid password",
                        color = Color.Red,
                        modifier = Modifier.padding(start = 1.dp)
                    )

                }
        }
    }
}