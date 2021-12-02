package org.sopt.sopthub.ui.view.user

import android.os.Bundle
import android.util.Log
import org.sopt.sopthub.R
import org.sopt.sopthub.data.remote.api.UserServiceCreator
import org.sopt.sopthub.data.remote.model.request.ReqSignUpData
import org.sopt.sopthub.data.remote.model.response.ResSignUpData
import org.sopt.sopthub.databinding.ActivitySignUpBinding
import org.sopt.sopthub.ui.base.BindingActivity
import org.sopt.sopthub.util.shortToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSignUpBtnClick()
    }

    private fun initSignUpBtnClick() {
        with(binding) {
            btnSignUp.setOnClickListener {
                postSignUp(
                    ReqSignUpData(
                        etId.text.toString(),
                        etName.text.toString(),
                        etPw.text.toString()
                    )
                )
            }
        }
    }

    private fun postSignUp(reqSignUpData: ReqSignUpData) {
        val call: Call<ResSignUpData> = UserServiceCreator.userService.postSignUp(reqSignUpData)

        call.enqueue(object : Callback<ResSignUpData> {
            override fun onResponse(call: Call<ResSignUpData>, response: Response<ResSignUpData>) {
                if (response.isSuccessful) {
                    finish()
                } else
                    shortToast("중복 이메일 또는 입력되지 않은 정보가 있습니다.")
            }

            override fun onFailure(call: Call<ResSignUpData>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })
    }
}