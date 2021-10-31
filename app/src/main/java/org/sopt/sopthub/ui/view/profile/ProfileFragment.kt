package org.sopt.sopthub.ui.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sopthub.R
import org.sopt.sopthub.databinding.FragmentProfileBinding
import org.sopt.sopthub.ui.base.BindingFragment

class ProfileFragment : BindingFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        initTransactionEvent()

        return binding.root
    }

    private fun initTransactionEvent() {
        val followerFragment = FollowerListFragment()
        val repositoryFragment = RepositoryListFragment()

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