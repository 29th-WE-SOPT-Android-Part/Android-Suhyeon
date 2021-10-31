package org.sopt.sopthub.ui.view

import android.os.Bundle
import org.sopt.sopthub.R
import org.sopt.sopthub.databinding.ActivitySignUpBinding
import org.sopt.sopthub.ui.base.BindingActivity
import org.sopt.sopthub.util.shortToast

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSignUpBtnClick()
    }

    private fun initSignUpBtnClick() {
        with(binding) {
            btnSignUp.setOnClickListener {
                if (etName.text.isNotBlank() && etId.text.isNotBlank() && etPw.text.isNotBlank()) {
                    finish()
                } else {
                    shortToast("입력되지 않은 정보가 있습니다")
                }
            }
        }
    }
}