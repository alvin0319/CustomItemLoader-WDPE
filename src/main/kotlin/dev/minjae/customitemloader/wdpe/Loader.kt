package dev.minjae.customitemloader.wdpe

import dev.minjae.customitemloader.wdpe.handler.DownstreamPacketHandler
import dev.waterdog.waterdogpe.event.defaults.InitialServerConnectedEvent
import dev.waterdog.waterdogpe.network.session.bedrock.BedrockDefaultClient
import dev.waterdog.waterdogpe.plugin.Plugin

class Loader : Plugin() {

    override fun onEnable() {
        proxy.eventManager.subscribe(InitialServerConnectedEvent::class.java) { event ->
            val player = event.player
            player.pluginDownstreamHandler =
                DownstreamPacketHandler((player.downstream as BedrockDefaultClient).session.session)
        }
    }
}
