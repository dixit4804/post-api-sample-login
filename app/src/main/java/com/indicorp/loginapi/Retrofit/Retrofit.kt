package com.indicorp.loginapi.Retrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit{
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "base url here"
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}
