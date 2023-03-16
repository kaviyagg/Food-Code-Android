data class LoginResponse(
    val dob: Number?,
    val email: String,
    val firstName: String,
    val gender:String?,
    val id:Number,
    val lastName:String,
    val scale: String,
    val token:String,
    val zipcode: Number?,
)

data class LoginRequest(val email: String, val password: String)

data class FoodCategory(val name: String, val raw: List<FoodItem>)
data class FoodItem(val name: String, val description: String, val category: String, val code: String)





