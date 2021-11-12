package org.sopt.sopthub.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserServiceCreator {
    private const val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userService : UserService = retrofit.create(UserService::class.java)
}