package org.sopt.sopthub.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.sopthub.data.RepoData
import org.sopt.sopthub.databinding.FragmentRepositoryBinding
import org.sopt.sopthub.ui.adapter.RepoAdapter

class RepositoryFragment : Fragment() {
    private lateinit var repoAdapter: RepoAdapter
    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding ?: error("binding이 초기화되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(layoutInflater, container, false)

        initAdapter()

        return binding.root
    }

    private fun initAdapter() {
        repoAdapter = RepoAdapter()
        binding.rvRepo.adapter = repoAdapter

        repoAdapter.repoList.addAll(
            listOf(
                RepoData("안드로이드 과제\n레포지토리", "안드 과제 레포입니다."),
                RepoData("iOS 과제\n레포지토리", "iOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOSiOS"),
                RepoData("서버 과제\n레포지토리", "서버 과제"),
                RepoData("웹 과제\n레포지토리", "웹 과제")
            )
        )

        repoAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}