package com.iptv.player.data.parser

import com.iptv.player.data.model.Channel
import java.net.URLDecoder

class M3UParser {

    fun parseM3U(content: String): List<Channel> {
        val channels = mutableListOf<Channel>()
        val lines = content.split("\n")
        var currentChannel: Channel.Builder? = null

        for (line in lines) {
            val trimmedLine = line.trim()

            when {
                trimmedLine.startsWith("#EXTINF:") -> {
                    currentChannel = Channel.Builder()
                    parseExtinf(trimmedLine, currentChannel)
                }
                trimmedLine.isNotEmpty() && !trimmedLine.startsWith("#") -> {
                    currentChannel?.apply {
                        url = trimmedLine
                        channels.add(build())
                    }
                    currentChannel = null
                }
            }
        }

        return channels
    }

    private fun parseExtinf(line: String, builder: Channel.Builder) {
        // Format: #EXTINF:-1 tvg-id="id" tvg-name="name" tvg-logo="logo" group-title="group",Channel Name
        val attrs = extractAttributes(line)

        builder.apply {
            tvgId = attrs["tvg-id"]
            tvgName = attrs["tvg-name"] ?: attrs["tvg-chno"]
            logo = attrs["tvg-logo"]
            group = attrs["group-title"]
            
            // Extract channel name (after comma)
            val nameStart = line.lastIndexOf(",") + 1
            if (nameStart > 0 && nameStart < line.length) {
                name = line.substring(nameStart).trim()
            }

            id = tvgId ?: name
        }
    }

    private fun extractAttributes(line: String): Map<String, String> {
        val attrs = mutableMapOf<String, String>()
        val pattern = """(\w+[\w-]*)="([^"]*)"""".toRegex()

        pattern.findAll(line).forEach { match ->
            val key = match.groupValues[1]
            val value = match.groupValues[2]
            attrs[key] = try {
                URLDecoder.decode(value, "UTF-8")
            } catch (e: Exception) {
                value
            }
        }

        return attrs
    }

    private class Channel.Builder {
        var id: String = ""
        var name: String = ""
        var logo: String? = null
        var url: String = ""
        var category: String? = null
        var group: String? = null
        var tvgId: String? = null
        var tvgName: String? = null
        var epg: String? = null

        fun build(): Channel = Channel(
            id = id.ifEmpty { name },
            name = name,
            logo = logo,
            url = url,
            category = group,
            group = group,
            epg = epg,
            tvgId = tvgId,
            tvgName = tvgName
        )
    }
}
