package org.sopt.sopthub.ui.view.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import org.sopt.sopthub.R
import org.sopt.sopthub.data.remote.api.UserServiceCreator
import org.sopt.sopthub.data.remote.model.request.ReqSignInData
import org.sopt.sopthub.data.remote.model.response.ResSignInData
import org.sopt.sopthub.databinding.ActivitySignInBinding
import org.sopt.sopthub.ui.base.BindingActivity
import org.sopt.sopthub.ui.view.MainActivity
import org.sopt.sopthub.util.shortToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSignInBtnClick()
        initSignUpBtnClick()
    }

    private fun initSignInBtnClick() {
        with(binding) {
            btnLogin.setOnClickListener {
                postSignIn(
                    ReqSignInData(
                        binding.etId.text.toString(),
                        binding.etPw.text.toString()
                    )
                )
            }
        }
    }

    private fun initSignUpBtnClick() {
        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun postSignIn(reqSignInData: ReqSignInData) {
        val call: Call<ResSignInData> = UserServiceCreator.userService.postSignIn(reqSignInData)

        call.enqueue(object : Callback<ResSignInData> {
            override fun onResponse(call: Call<ResSignInData>, response: Response<ResSignInData>) {
                if (response.isSuccessful) {
                    val data = response.body()?.data

                    shortToast("${data?.email}님 반갑습니다!")
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                } else
                    shortToast("로그인에 실패하셨습니다.")
            }

            override fun onFailure(call: Call<ResSignInData>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })
    }
}