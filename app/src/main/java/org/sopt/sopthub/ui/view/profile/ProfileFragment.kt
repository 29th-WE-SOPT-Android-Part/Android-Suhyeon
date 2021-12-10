package org.sopt.sopthub.ui.view.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sopthub.R
import org.sopt.sopthub.databinding.FragmentProfileBinding
import org.sopt.sopthub.ui.base.BindingFragment
import org.sopt.sopthub.ui.view.setting.SettingActivity

class ProfileFragment : BindingFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        initProfile()
        initTransactionEvent()
        initSettingBtnClick()

        return binding.root
    }

    private fun initTransactionEvent() {
        val followerFragment = FollowerListFragment()
        val repositoryFragment = RepositoryListFragment()

        childFragmentManager.beginTransaction().add(R.id.container_rec, followerFragment).commit()

        binding.btnFollower.setOnClickListener {
            with(binding) {
                btnFollower.isSelected = true
                btnRepo.isSelected = false
            }
            childFragmentManager.beginTransaction().replace(R.id.container_rec, followerFragment)
                .commit()
        }

        binding.btnRepo.setOnClickListener {
            with(binding) {
                btnFollower.isSelected = false
                btnRepo.isSelected = true
            }
            childFragmentManager.beginTransaction()
                .replace(R.id.container_rec, repositoryFragment).commit()
        }
    }

    private fun initProfile() {
        with(binding) {
            btnFollower.isSelected = true
            imgUrl = "https://cdn.pixabay.com/photo/2020/10/21/19/43/jack-o-lanterns-5674148_960_720.jpg"
        }
    }

    private fun initSettingBtnClick() {
        binding.ivSetting.setOnClickListener {
            startActivity(Intent(requireContext(), SettingActivity::class.java))
        }
    }
}