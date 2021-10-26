package org.sopt.sopthub.ui.view

import android.os.Bundle
import org.sopt.sopthub.R
import org.sopt.sopthub.databinding.ActivityHomeBinding
import org.sopt.sopthub.ui.base.BindingActivity

class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTransactionEvent()
    }

    private fun initTransactionEvent() {
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()

        supportFragmentManager.beginTransaction().add(R.id.container_rec, followerFragment).commit()

        binding.btnFollower.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.container_rec, followerFragment)
                .commit()
        }

        binding.btnRepo.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_rec, repositoryFragment).commit()
        }
    }
}