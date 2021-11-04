package org.sopt.sopthub.ui.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sopthub.R
import org.sopt.sopthub.databinding.FragmentFollowerTabBinding
import org.sopt.sopthub.ui.base.BindingFragment

class FollowerTabFragment : BindingFragment<FragmentFollowerTabBinding>(R.layout.fragment_follower_tab) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }
}