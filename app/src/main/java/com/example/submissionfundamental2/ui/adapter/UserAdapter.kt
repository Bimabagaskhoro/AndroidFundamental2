package com.example.submissionfundamental2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissionfundamental2.data.UserResponse
import com.example.submissionfundamental2.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val list = ArrayList<UserResponse>()

    fun setList(userResponses: ArrayList<UserResponse>) {
        list.clear()
        list.addAll(userResponses)
        notifyDataSetChanged()
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class UserViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userResponse: UserResponse) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(userResponse)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(userResponse.avatar_url)
                    .apply(RequestOptions().override(55, 55))
                    .into(imgUser)
                tvUsername.text = userResponse.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder((view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    interface OnItemClickCallback {
        fun onItemClicked(data: UserResponse)
    }

}