import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodcode.*

@Composable
fun MyAppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(Screen.Landing.route) {
            LandingPage(navController = navController)
        }
        composable(Screen.Login.route) {
            Login(navController = navController)
        }
        composable(Screen.MainScreen.route){
            mainScreen(navController = navController)
        }
        composable(Screen.SignUp.route){
            SignUpPage(navController = navController)
        }
        composable(Screen.SignupPage.route){
            SignUp(navController = navController)
        }
    }
}