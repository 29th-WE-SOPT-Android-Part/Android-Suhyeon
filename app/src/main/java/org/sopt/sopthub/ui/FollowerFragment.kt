package org.sopt.sopthub.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.sopthub.data.FollowerData
import org.sopt.sopthub.databinding.FragmentFollowerBinding
import org.sopt.sopthub.ui.adapter.FollowerAdapter

class FollowerFragment : Fragment() {
    private lateinit var followerAdapter: FollowerAdapter
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding ?: error("binding이 초기화되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)

        initAdapter()

        return binding.root
    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter

        followerAdapter.userList.addAll(
            listOf(
                FollowerData("img link","문다빈", "안드로이드 파트장"),
                FollowerData("img link","장혜령", "iOS 파트장"),
                FollowerData("img link","김우영", "서버파트장")
            )
        )

        followerAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}