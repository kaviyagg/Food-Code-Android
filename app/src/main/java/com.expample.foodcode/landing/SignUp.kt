package com.example.foodcode

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.foodcode.ui.theme.LightGreen

@Composable
fun SignUp(navController: NavHostController,){
    val signUpViewModel = viewModel<SignUpViewModel>()
    Scaffold(
       topBar={
           TopAppBar(backgroundColor = LightGreen,
           elevation = 20.dp) {
               Text("Sign Up",
               modifier = Modifier
                   .fillMaxWidth()
                   .size(12.dp)
                   .wrapContentWidth(Alignment.CenterHorizontally),
                   fontWeight = FontWeight.Bold,)
           }
       }
    ) {
SignUpScreen()
}
    }



@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel = viewModel()) {
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = signUpViewModel.email.value,
            onValueChange = { signUpViewModel.email.value = it },
            label = { Text("Email") },
            modifier = Modifier.padding(16.dp)
        )

        OutlinedTextField(
            value = signUpViewModel.password.value,
            onValueChange = { signUpViewModel.password.value = it },
            label = { Text("Password") },
            modifier = Modifier.padding(16.dp)
        )

        OutlinedTextField(
            value = signUpViewModel.confirmPassword.value,
            onValueChange = { signUpViewModel.confirmPassword.value = it },
            label = { Text("Confirm Password") },
            modifier = Modifier.padding(16.dp)
        )

        Button(
            onClick = {
                if (signUpViewModel.isValid() && !isLoading) {
                    isLoading = true
                    // perform sign-up action here
                }
            },
            enabled = signUpViewModel.isValid() && !isLoading,
            modifier = Modifier.padding(16.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Text("Sign Up")
            }
        }
    }
}