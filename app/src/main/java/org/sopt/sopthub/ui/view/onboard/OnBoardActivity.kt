package org.sopt.sopthub.ui.view.onboard

import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import org.sopt.sopthub.R
import org.sopt.sopthub.data.local.SOPTHubSharedPreferences
import org.sopt.sopthub.databinding.ActivityOnBoardBinding
import org.sopt.sopthub.ui.base.BindingActivity
import org.sopt.sopthub.ui.view.user.SignInActivity

class OnBoardActivity : BindingActivity<ActivityOnBoardBinding>(R.layout.activity_on_board) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolBar()
        isOnBoarding()
    }

    private fun initToolBar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_on_board) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun isOnBoarding() {
        if (SOPTHubSharedPreferences.getIsOnBoarding()) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }
}