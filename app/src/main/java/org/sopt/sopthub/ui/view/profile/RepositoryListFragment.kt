package org.sopt.sopthub.ui.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sopthub.R
import org.sopt.sopthub.data.RepoData
import org.sopt.sopthub.databinding.FragmentRepositoryListBinding
import org.sopt.sopthub.ui.base.BindingFragment
import org.sopt.sopthub.ui.view.adapter.RepoAdapter

class RepositoryListFragment :
    BindingFragment<FragmentRepositoryListBinding>(R.layout.fragment_repository_list) {
    private lateinit var repoAdapter: RepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        initAdapter()

        return binding.root
    }

    private fun initAdapter() {
        repoAdapter = RepoAdapter()
        binding.rvRepo.adapter = repoAdapter

        repoAdapter.repoList.addAll(
            listOf(
                RepoData("안드로이드 과제 레포지토리", "안드 과제 레포입니다."),
                RepoData("iOS 과제 레포지토리", "iOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOS"),
                RepoData("서버 과제 레포지토리", "서버 과제"),
                RepoData("웹 과제 레포지토리", "웹 과제")
            )
        )

        repoAdapter.notifyDataSetChanged()
    }
}