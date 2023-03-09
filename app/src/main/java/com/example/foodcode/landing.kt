package com.example.foodcode

import Screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.foodcode.ui.theme.LightGreen
import com.example.foodcode.ui.theme.LightGrey


@Composable
fun LandingPage(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxHeight(),
        color= LightGreen,
    ) {
        Column() {
            FoodCodeIcon()
            Spacer(modifier = Modifier.height(40.dp))
            Column( modifier = Modifier.padding(40.dp , 0.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)){
                Buttons(LightGreen,Text ="SIGN UP",backgroundColor = Color.White,navController, route = Screen.SignUp.route)
                Buttons(Color.White, Text = "LOG IN",backgroundColor = LightGrey,navController, route = Screen.Login.route)
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxHeight(1f)
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        BottomButton(Text="Version 2.0.0 | PRIVACY POLICY" )
        BottomButton(Text = "QUESTIONS?")
    }
    }


// above function
@Composable
fun BottomButton(Text: String){
    Text(text = Text,
        style = TextStyle(
            Color.White,
            fontSize = 16.sp)
    )
}

@Composable
fun FoodCodeIcon() {
    val imageModifier = Modifier.fillMaxWidth(fraction = .8f)
        .wrapContentHeight()
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Image(
            painter = painterResource(id = R.drawable.icon), contentDescription = null,
            modifier = imageModifier
        )
    }
}

@Composable
fun Buttons(color: Color, Text: String, backgroundColor: Color ,navController: NavController, route: String){
    Button(
        onClick = {
            navController.navigate(route)
                  },
        colors = ButtonDefaults.buttonColors( backgroundColor),
        shape = RoundedCornerShape(60.dp),
        modifier = Modifier
            .fillMaxWidth(8f)
            .height(85.dp)
    ) {
        Text(text = Text,
            style = TextStyle(
                color = color,
                fontWeight = FontWeight.Normal,
                fontSize = 35.sp,
            )
        )
    }
}
