

package com.example.foodcode
import FoodCategory
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Composable
fun mainScreen(navController: NavController){
    val context = LocalContext.current
    val jsonString = context.assets.open("categories.json").bufferedReader().use { it.readText() }
    val foodCategories = Gson().fromJson<Map<String, List<String>>>(jsonString, object : TypeToken<Map<String, List<String>>>() {}.type)
    val foodCategoryList = foodCategories.map { FoodCategory(it.key, it.value) }

    LazyColumn {
        items(foodCategoryList) { category ->
            Column(
                modifier = Modifier.padding(5.dp, 16.dp)
            ) {
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.h6
                )
                Divider(
                    color = Color.White,
                    thickness = 0.5.dp,
                    modifier = Modifier
                        .padding(start = 0.dp)
                        .height(1.dp)
                )
                category.items.forEach { item ->

                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}




