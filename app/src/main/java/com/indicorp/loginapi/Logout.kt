package com.indicorp.loginapi.Logout


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.indicorp.loginapi.MainActivity
import com.indicorp.loginapi.R

class LogoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        val btnLogout: Button = findViewById(R.id.btnlogout)
        btnLogout.setOnClickListener {
            performLogout()
        }
    }

    private fun performLogout() {
        // Clear any saved user session or credentials here, if necessary.
        // For example, you might clear shared preferences, user tokens, etc.

        // After clearing the session, start the MainActivity to log the user out.
        val intent = Intent(this, MainActivity::class.java)
        // Add any other flags or extras as needed for your app.
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
