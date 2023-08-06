package com.indicorp.loginapi.Service

import com.indicorp.loginapi.Data.UserLogin
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIClient {
    @POST("api end point here")
    fun loginApi(@Body requestBody: RequestBody): Call<UserLogin>
}


