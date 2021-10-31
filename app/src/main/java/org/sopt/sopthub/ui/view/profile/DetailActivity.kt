package org.sopt.sopthub.ui.view.profile

import android.os.Bundle
import org.sopt.sopthub.R
import org.sopt.sopthub.data.FollowerData
import org.sopt.sopthub.databinding.ActivityDetailBinding
import org.sopt.sopthub.ui.base.BindingActivity

class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFollowerInfo()
        initBackBtnClick()
    }

    private fun initFollowerInfo() {
        val bundle = intent.extras
        bundle?.let{
            val name = it.getString("name") ?: ""
            val introduce = it.getString("introduce") ?: ""
            binding.userInfoData = FollowerData("imgUrl", name, introduce)
        }
    }

    private fun initBackBtnClick() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}