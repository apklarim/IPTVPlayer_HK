package com.iptv.player.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iptv.player.data.model.Channel
import com.iptv.player.databinding.ItemChannelBinding

class ChannelAdapter(
    private val onChannelClick: (Channel) -> Unit
) : ListAdapter<Channel, ChannelAdapter.ChannelViewHolder>(ChannelDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val binding = ItemChannelBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChannelViewHolder(binding, onChannelClick)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ChannelViewHolder(
        private val binding: ItemChannelBinding,
        private val onChannelClick: (Channel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(channel: Channel) {
            binding.apply {
                channelNameTextView.text = channel.name

                // Load logo
                if (!channel.logo.isNullOrEmpty()) {
                    Glide.with(itemView.context)
                        .load(channel.logo)
                        .into(channelLogoImageView)
                }

                // Set favorite indicator
                favoriteIcon.visibility = if (channel.isFavorite) android.view.View.VISIBLE else android.view.View.GONE

                root.setOnClickListener {
                    onChannelClick(channel)
                }
            }
        }
    }

    class ChannelDiffCallback : DiffUtil.ItemCallback<Channel>() {
        override fun areItemsTheSame(oldItem: Channel, newItem: Channel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Channel, newItem: Channel): Boolean {
            return oldItem == newItem
        }
    }
}
