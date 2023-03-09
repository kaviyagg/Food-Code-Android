import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson

fun deleteAllUserData(context: Context) {
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    val editor = prefs.edit()
    editor.clear()
    editor.apply()
}

fun savePeople(context: Context, User: List<LoginResponse>) {
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    val editor = prefs.edit()
    val json = Gson().toJson(User)
    editor.putString("User", json)
    editor.apply()
}

fun getPeople(context: Context): List<LoginResponse> {
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    val json = prefs.getString("User", null)
    return if (json != null) {
        Gson().fromJson(json, Array<LoginResponse>::class.java).toList()
    } else {
        emptyList()
    }
}