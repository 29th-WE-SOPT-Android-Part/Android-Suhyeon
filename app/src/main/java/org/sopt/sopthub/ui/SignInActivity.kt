package org.sopt.sopthub.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sopthub.databinding.ActivitySignInBinding
import org.sopt.sopthub.util.shortToast

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignInBtnClick()
        initSignUpBtnClick()
    }

    private fun initSignInBtnClick() {
        with(binding) {
            btnLogin.setOnClickListener {
                if (etId.text.isNotBlank() && etPw.text.isNotBlank()) {
                    shortToast("강수현님 환영합니다")
                    startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
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