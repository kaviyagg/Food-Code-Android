import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route:String){
    object Landing : Screen("Landing")
    object Login: Screen("Login")
    object SignUp: Screen("Signup")
    object MainScreen: Screen("mainScreen")
    object SignupPage: Screen("signupPage")
}
data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)
//val bottomNavItems = listOf(
//    BottomNavItem(
//        name = "BACK",
//        route = "Landing",
//        icon = Icons.Rounded.ArrowBack,
//    ),
//    BottomNavItem(
//        name = "LOG IN",
//        route = "Login",
//        icon = Icons.Rounded.ArrowForward
//    )
//)