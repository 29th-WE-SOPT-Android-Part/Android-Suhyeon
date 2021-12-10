package org.sopt.sopthub.ui.view.setting

import android.os.Bundle
import org.sopt.sopthub.R
import org.sopt.sopthub.databinding.ActivitySettingBinding
import org.sopt.sopthub.ui.base.BindingActivity
import org.sopt.sopthub.data.local.SOPTHubSharedPreferences

class SettingActivity : BindingActivity<ActivitySettingBinding>(R.layout.activity_setting) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRemoveAutoLoginClick()
    }

    private fun initRemoveAutoLoginClick() {
        binding.tvRemoveAutoLogin.setOnClickListener {
            SOPTHubSharedPreferences.removeAutoLogin()
        }
    }
}