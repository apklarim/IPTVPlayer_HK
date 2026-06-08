package com.iptv.player.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.iptv.player.databinding.ActivityVideoPlayerBinding

class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoPlayerBinding
    private var player: ExoPlayer? = null
    private var channelUrl: String = ""
    private var channelName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        channelUrl = intent.getStringExtra("channel_url") ?: ""
        channelName = intent.getStringExtra("channel_name") ?: ""

        initializePlayer()
        updateUI()
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(this).build()
        binding.playerView.player = player

        if (channelUrl.isNotEmpty()) {
            val mediaItem = MediaItem.fromUri(channelUrl)
            player?.setMediaItem(mediaItem)
            player?.prepare()
            player?.play()
        }
    }

    private fun updateUI() {
        title = channelName
        binding.channelNameTextView.text = channelName
    }

    override fun onPause() {
        super.onPause()
        player?.pause()
    }

    override fun onResume() {
        super.onResume()
        player?.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
    }
}
