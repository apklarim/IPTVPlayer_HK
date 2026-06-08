package com.iptv.player.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.iptv.player.data.model.Channel
import com.iptv.player.data.repository.IPTVDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChannelsViewModel(application: Application) : AndroidViewModel(application) {

    private val database = IPTVDatabase.getInstance(application)
    private val channelDao = database.channelDao()

    private val _channels = MutableStateFlow<List<Channel>>(emptyList())
    val channels: StateFlow<List<Channel>> = _channels

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadChannels()
    }

    private fun loadChannels() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                // Load from database
                channelDao.getChannelsByPlaylist("default").collect { entities ->
                    _channels.value = entities.map { entity ->
                        Channel(
                            id = entity.id,
                            name = entity.name,
                            logo = entity.logo,
                            url = entity.url,
                            category = entity.category,
                            group = entity.group,
                            isFavorite = entity.isFavorite,
                            epg = entity.epg,
                            tvgId = entity.tvgId,
                            tvgName = entity.tvgName
                        )
                    }
                }
                _isLoading.value = false
            } catch (e: Exception) {
                _error.value = e.message
                _isLoading.value = false
            }
        }
    }

    fun onChannelSelected(channel: Channel) {
        // Navigate to player
    }

    fun refreshChannels() {
        loadChannels()
    }
}
