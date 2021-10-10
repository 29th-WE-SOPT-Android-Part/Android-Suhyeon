package org.sopt.sopthub
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sopthub.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignUpBtnClick()
    }

    private fun initSignUpBtnClick() {
        binding.btnSignUpFinish.setOnClickListener {
            if (binding.etName.text.isNotBlank() && binding.etId.text.isNotBlank() && binding.etPw.text.isNotBlank()) {
                finish()
            } else {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}