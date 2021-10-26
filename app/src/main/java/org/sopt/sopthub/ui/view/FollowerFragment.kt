package org.sopt.sopthub.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sopthub.R
import org.sopt.sopthub.data.FollowerData
import org.sopt.sopthub.databinding.FragmentFollowerBinding
import org.sopt.sopthub.ui.base.BindingFragment
import org.sopt.sopthub.ui.view.adapter.FollowerAdapter

class FollowerFragment : BindingFragment<FragmentFollowerBinding>(R.layout.fragment_follower) {
    private lateinit var followerAdapter: FollowerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        initAdapter()

        return binding.root
    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter

        followerAdapter.userList.addAll(
            listOf(
                FollowerData("img link", "문다빈", "안드로이드 파트장"),
                FollowerData("img link", "장혜령", "iOS 파트장"),
                FollowerData("img link", "김우영", "서버파트장")
            )
        )

        followerAdapter.notifyDataSetChanged()

        followerAdapter.setFollowerItemClickListener { imgUrl, name, introduce ->
            navigateDetail(imgUrl, name, introduce)
        }
    }

    private fun navigateDetail(imgUrl: String, name: String, introduce: String) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        val bundle = Bundle().apply {
            putString("imgUrl", imgUrl)
            putString("name", name)
            putString("introduce", introduce)
        }
        intent.putExtras(bundle)
        startActivity(intent)
    }
}