package com.indicorp.loginapi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.indicorp.loginapi.Data.LoginModels
import com.indicorp.loginapi.Data.UserLogin
import com.indicorp.loginapi.Logout.LogoutActivity
import com.indicorp.loginapi.Retrofit.Retrofit
import com.indicorp.loginapi.Service.APIClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private var apiServ: APIClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        val retrofit = Retrofit.retrofitInstance
        if (retrofit != null) {
            apiServ = retrofit.create(APIClient::class.java)
            btnLogin.setOnClickListener {
                getDataFromApi()

            }
            Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
        }


//        fun compareCredentials(userLogin: UserLogin?) {
//            if (userLogin != null) {
//                val inputEmail = etUsername.text.toString()
//                val inputPassword = etPassword.text.toString()
//
//                if (inputEmail == userLogin.useremail.Email && inputPassword == userLogin.userpassword.Password) {
////                    navigateToNewActivity()
//                    val intent = Intent(this@MainActivity, LogoutActivity::class.java)
//                    startActivity(intent)
//                } else {
//                    Toast.makeText(this, "Incorrect credentials. Please try again.", Toast.LENGTH_SHORT).show()
//                }
//            } else {
//                Toast.makeText(this, "Login data is null. Please try again.", Toast.LENGTH_SHORT).show()
//            }
//        }

    }

    private fun getDataFromApi() {
        val requestData = LoginModels(
            etUsername.text.toString().trim(),
            etPassword.text.toString().trim()
        )
        val gson = Gson()
        val jsonBody = gson.toJson(requestData)
        val requestBody = jsonBody.toRequestBody("application/json".toMediaTypeOrNull())
        val call: Call<UserLogin>? = apiServ?.loginApi(requestBody)
        call?.enqueue(object : Callback<UserLogin?> {
            override fun onResponse(
                call: Call<UserLogin?>?,
                response: Response<UserLogin?>
            ) {
                if (response.isSuccessful) {
                    val data: UserLogin? = response.body()
                    val message = data?.message
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserLogin?>?, t: Throwable?) {
                // Handle network or other errors...
                Toast.makeText(this@MainActivity, "Failed to connect to the server", Toast.LENGTH_SHORT).show()
            }
        })
    }

//    private fun navigateToNewActivity() {
//        val intent = Intent(this@MainActivity, LogoutActivity::class.java)
//        startActivity(intent)
//    }
}