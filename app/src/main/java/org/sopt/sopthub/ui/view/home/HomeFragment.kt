package org.sopt.sopthub.ui.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import org.sopt.sopthub.R
import org.sopt.sopthub.databinding.FragmentHomeBinding
import org.sopt.sopthub.ui.base.BindingFragment
import org.sopt.sopthub.ui.view.home.adapter.FollowTabPagerAdapter

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var followTabPagerAdapter: FollowTabPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        initFollowTabPagerAdapter()
        initFollowTab()
        return binding.root
    }

    private fun initFollowTabPagerAdapter() {
        val fragmentList = listOf(FollowerTabFragment(), FollowingTabFragment())

        followTabPagerAdapter = FollowTabPagerAdapter(this)
        followTabPagerAdapter.fragments.addAll(fragmentList)

        binding.vpFollow.adapter = followTabPagerAdapter
    }

    private fun initFollowTab() {
        val tabLabel = listOf("팔로워", "팔로잉")

        TabLayoutMediator(binding.tlFollow, binding.vpFollow) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }
}