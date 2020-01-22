package com.dezzapps.orange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import com.dezzapps.orange.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_show_orange_liking_users.setOnClickListener{

            val users = getUsers()
            val userLikingOranges = users.filter { it.likeOranges }

            textView.text = userLikingOranges.joinToString()
        }

        btn_save_users_to_preferences.setOnClickListener {

            val usersToSave = listOf(User("Harry", true), User("Anddres", false),User("Lopez", true),
                User("Manuel", true),User("Harry", true))

            saveUsersToPreferences(usersToSave)

            Toast.makeText(this, "Saved ${usersToSave.size} users to preferences.", Toast.LENGTH_SHORT).show()
        }


    }

    private fun saveUsersToPreferences(users: List<User>){
        val prefEditor = PreferenceManager.getDefaultSharedPreferences(this).edit()
        val jsonString = Gson().toJson(users)
        prefEditor.putString("users", jsonString).apply()
    }

    private fun getUsers(): List<User>{
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val jsonString = preferences.getString("users", null)

        return if (jsonString != null)
            Gson().fromJson(jsonString)
        else
            listOf()
    }
}
