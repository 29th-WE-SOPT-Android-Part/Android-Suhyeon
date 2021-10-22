package org.sopt.sopthub.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sopthub.R
import org.sopt.sopthub.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        initTransactionEvent()

        setContentView(binding.root)
    }

    private fun initTransactionEvent() {
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()

        supportFragmentManager.beginTransaction().add(R.id.container_rec, followerFragment).commit()

        binding.btnFollower.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.container_rec, followerFragment).commit()
        }

        binding.btnRepo.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.container_rec, repositoryFragment).commit()
        }
    }
}