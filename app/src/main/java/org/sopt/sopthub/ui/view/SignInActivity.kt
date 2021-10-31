package org.sopt.sopthub.ui.view

import android.content.Intent
import android.os.Bundle
import org.sopt.sopthub.R
import org.sopt.sopthub.databinding.ActivitySignInBinding
import org.sopt.sopthub.ui.base.BindingActivity
import org.sopt.sopthub.util.shortToast

class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSignInBtnClick()
        initSignUpBtnClick()
    }

    private fun initSignInBtnClick() {
        with(binding) {
            btnLogin.setOnClickListener {
                if (etId.text.isNotBlank() && etPw.text.isNotBlank()) {
                    shortToast("강수현님 환영합니다")
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                } else {
                    shortToast("로그인 실패")
                }
            }
        }
    }

    private fun initSignUpBtnClick() {
        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}