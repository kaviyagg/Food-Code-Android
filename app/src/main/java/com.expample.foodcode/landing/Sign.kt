package com.example.foodcode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.foodcode.ui.theme.LightGreen

@Composable
fun SignUpPage(
    navController: NavHostController,
) {
    val signUpViewModel = viewModel<SignUpViewModel>()
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = LightGreen,
                elevation = 20.dp

            )
            {
                Text("SignUp",
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                )
            }
        },
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = LightGreen,
                elevation = 8.dp
            ) {
                Button(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .background(LightGreen),
                    onClick = {
                        navController.navigate(Screen.SignupPage.route)},
                    enabled = signUpViewModel.isValid()
                ) {
                    Text(text = "Sign Up")
                }
            }
        }
    ) {
        Card(  modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .fillMaxWidth(),
            elevation = 20.dp

            ) {


            Column(
                modifier = Modifier
//                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                TextField(
                    modifier = Modifier.padding(vertical = 8.dp),
                    value = signUpViewModel.firstName.value,
                    onValueChange = { signUpViewModel.firstName.value = it },
                    label = { Text("First Name") },
                isError = !signUpViewModel.isFirstNameValid() && signUpViewModel.firstName.value.isNotEmpty(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                ),
                )
                if (!signUpViewModel.isFirstNameValid()) {
                    Text(
                        text = "Must use a valid password",
                        color = Color.Red,
                        modifier = Modifier.padding(top = 4.dp, start = 10.dp)
                    )
                }

                TextField(
                    modifier = Modifier.padding(vertical = 8.dp),
                    value = signUpViewModel.lastName.value,
                    onValueChange = { signUpViewModel.lastName.value = it },
                    label = { Text("Last Name") },
                    isError = !signUpViewModel.isLastNameValid() && signUpViewModel.lastName.value.isNotEmpty(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text
                    ),
                )
                var selectedOption by remember { mutableStateOf(0) }
                val options = listOf("Amazon (Model #451)","Bed,Bath,& Beyond / Target (Model #450)")

                Column {
                    options.forEachIndexed { index, text ->
                        Row(
                            Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = text,
                                modifier = Modifier.weight(1f).padding(start = 16.dp)
                            )
                            RadioButton(
                                selected = selectedOption == index,
                                onClick = { selectedOption = index }
                            )
                        }
                    }
                }
            }
        }
    }
}


