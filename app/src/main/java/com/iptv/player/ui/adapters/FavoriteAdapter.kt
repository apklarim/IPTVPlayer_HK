package com.iptv.player.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iptv.player.data.model.Favorite
import com.iptv.player.databinding.ItemFavoriteBinding

class FavoriteAdapter(
    private val onFavoriteClick: (Favorite) -> Unit
) : ListAdapter<Favorite, FavoriteAdapter.FavoriteViewHolder>(FavoriteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoriteViewHolder(binding, onFavoriteClick)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FavoriteViewHolder(
        private val binding: ItemFavoriteBinding,
        private val onFavoriteClick: (Favorite) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favorite: Favorite) {
            binding.apply {
                channelNameTextView.text = favorite.channelName

                // Load logo
                if (!favorite.channelLogo.isNullOrEmpty()) {
                    Glide.with(itemView.context)
                        .load(favorite.channelLogo)
                        .into(channelLogoImageView)
                }

                root.setOnClickListener {
                    onFavoriteClick(favorite)
                }
            }
        }
    }

    class FavoriteDiffCallback : DiffUtil.ItemCallback<Favorite>() {
        override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem == newItem
        }
    }
}
