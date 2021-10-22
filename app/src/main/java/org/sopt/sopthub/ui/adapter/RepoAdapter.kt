package org.sopt.sopthub.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sopthub.data.RepoData
import org.sopt.sopthub.databinding.ItemRepositoryBinding

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {
    val repoList = mutableListOf<RepoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : RepoViewHolder {
        val binding = ItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(repoList[position])
    }

    override fun getItemCount(): Int = repoList.size

    class RepoViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepoData) {
            binding.repoInfoData = data
        }
    }
}