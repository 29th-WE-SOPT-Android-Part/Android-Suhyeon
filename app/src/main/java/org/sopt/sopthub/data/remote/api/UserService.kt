package org.sopt.sopthub.data.remote.api

import org.sopt.sopthub.data.remote.model.request.ReqSignInData
import org.sopt.sopthub.data.remote.model.request.ReqSignUpData
import org.sopt.sopthub.data.remote.model.response.ResSignInData
import org.sopt.sopthub.data.remote.model.response.ResSignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    @Headers("Content-Type: application/json")
    @POST("user/login")
    fun postSignIn(
        @Body body : ReqSignInData
    ) : Call<ResSignInData>

    @Headers("Content-Type: application/json")
    @POST("user/signup")
    fun postSignUp(
        @Body body : ReqSignUpData
    ) : Call<ResSignUpData>
}