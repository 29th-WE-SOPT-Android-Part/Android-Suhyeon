package org.sopt.sopthub.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sopthub.R
import org.sopt.sopthub.databinding.FragmentHomeBinding
import org.sopt.sopthub.ui.base.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        initTransactionEvent()

        return binding.root
    }
    //이걸 꼭 해야되나?

    private fun initTransactionEvent() {
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()
//수정부탁 supportFragmentManager
        childFragmentManager.beginTransaction().add(R.id.container_rec, followerFragment).commit()

        binding.btnFollower.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.container_rec, followerFragment)
                .commit()
        }

        binding.btnRepo.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.container_rec, repositoryFragment).commit()
        }
    }
}