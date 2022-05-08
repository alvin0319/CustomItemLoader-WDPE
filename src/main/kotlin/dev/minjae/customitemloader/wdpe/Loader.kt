package dev.minjae.customitemloader.wdpe

import com.nukkitx.protocol.bedrock.packet.ItemComponentPacket
import com.nukkitx.protocol.bedrock.v419.serializer.ItemComponentSerializer_v419
import dev.minjae.customitemloader.wdpe.handler.DownstreamPacketHandler
import dev.waterdog.waterdogpe.event.defaults.InitialServerConnectedEvent
import dev.waterdog.waterdogpe.event.defaults.ProtocolCodecRegisterEvent
import dev.waterdog.waterdogpe.network.session.bedrock.BedrockDefaultClient
import dev.waterdog.waterdogpe.plugin.Plugin

class Loader : Plugin() {

    override fun onEnable() {
        proxy.eventManager.subscribe(InitialServerConnectedEvent::class.java) { event ->
            val player = event.player
            player.pluginDownstreamHandler =
                DownstreamPacketHandler((player.downstream as BedrockDefaultClient).session.session)
        }

        proxy.eventManager.subscribe(ProtocolCodecRegisterEvent::class.java) { event ->
            event.codecBuilder.registerPacket(
                ItemComponentPacket::class.java,
                ItemComponentSerializer_v419.INSTANCE,
                162
            )
        }
    }
}
