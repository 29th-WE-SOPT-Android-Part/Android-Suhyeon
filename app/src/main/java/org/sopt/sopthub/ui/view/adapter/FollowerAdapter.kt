package org.sopt.sopthub.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sopthub.data.FollowerData
import org.sopt.sopthub.databinding.ItemFollowerBinding
import org.sopt.sopthub.util.ItemTouchHelperListener

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(),
    ItemTouchHelperListener {
    val userList = mutableListOf<FollowerData>()

    private var followerItemClickListener: ((String, String, String) -> Unit)? = null

    fun setFollowerItemClickListener(listener: (String, String, String) -> Unit) {
        followerItemClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowerViewHolder {
        val binding = ItemFollowerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    inner class FollowerViewHolder(private val binding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FollowerData) {
            binding.userInfoData = data
            binding.clFollower.setOnClickListener {
                followerItemClickListener?.invoke(data.imgUrl, data.name, data.introduce)
            }
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        val item = userList[fromPosition]
        userList.removeAt(fromPosition)
        userList.add(toPosition, item)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemSwipe(position: Int) {
        userList.removeAt(position)
        notifyItemRemoved(position)
    }

}