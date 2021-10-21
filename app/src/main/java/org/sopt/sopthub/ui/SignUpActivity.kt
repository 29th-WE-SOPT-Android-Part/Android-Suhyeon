package org.sopt.sopthub.ui
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sopthub.databinding.ActivitySignUpBinding
import org.sopt.sopthub.util.shortToast

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignUpBtnClick()
    }

    private fun initSignUpBtnClick() {
        with(binding) {
            btnSignUpFinish.setOnClickListener {
                if (etName.text.isNotBlank() && etId.text.isNotBlank() && etPw.text.isNotBlank()) {
                    finish()
                } else {
                    shortToast("입력되지 않은 정보가 있습니다")
                }
            }
        }
    }
}