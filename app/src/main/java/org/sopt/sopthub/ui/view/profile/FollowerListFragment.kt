package org.sopt.sopthub.ui.view.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import org.sopt.sopthub.R
import org.sopt.sopthub.data.FollowerData
import org.sopt.sopthub.databinding.FragmentFollowerListBinding
import org.sopt.sopthub.ui.base.BindingFragment
import org.sopt.sopthub.ui.view.adapter.FollowerAdapter
import org.sopt.sopthub.util.FollowerItemDecoration
import org.sopt.sopthub.util.ItemTouchHelperCallback
import org.sopt.sopthub.util.dp

class FollowerListFragment :
    BindingFragment<FragmentFollowerListBinding>(R.layout.fragment_follower_list) {
    private lateinit var followerAdapter: FollowerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        initAdapter()
        //initFollowerItemDecoration()
        initItemTouchHelper()

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

    private fun initFollowerItemDecoration() {
        binding.rvFollower.addItemDecoration(
            FollowerItemDecoration(
                dividerHeight = 5.dp, padding = 5.dp,
                color = ContextCompat.getColor(requireContext(), R.color.sopt_pink)
            )
        )
    }

    private fun initItemTouchHelper() {
        ItemTouchHelper(ItemTouchHelperCallback(followerAdapter)).attachToRecyclerView(binding.rvFollower)
    }
}