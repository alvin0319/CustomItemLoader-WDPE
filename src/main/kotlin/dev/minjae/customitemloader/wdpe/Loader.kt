package dev.minjae.customitemloader.wdpe

import dev.minjae.customitemloader.wdpe.handler.DownstreamPacketHandler
import dev.waterdog.waterdogpe.event.defaults.InitialServerConnectedEvent
import dev.waterdog.waterdogpe.network.protocol.ProtocolCodecs
import dev.waterdog.waterdogpe.network.protocol.ProtocolVersion
import dev.waterdog.waterdogpe.network.protocol.updaters.ProtocolCodecUpdater
import dev.waterdog.waterdogpe.plugin.Plugin
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec
import org.cloudburstmc.protocol.bedrock.codec.v419.serializer.ItemComponentSerializer_v419
import org.cloudburstmc.protocol.bedrock.packet.ItemComponentPacket

class Loader : Plugin() {

    override fun onEnable() {
        proxy.eventManager.subscribe(InitialServerConnectedEvent::class.java) { event ->
            val player = event.player
            player.pluginPacketHandlers.add(DownstreamPacketHandler())
        }
        ProtocolCodecs.addUpdater(object : ProtocolCodecUpdater {
            override fun updateCodec(builder: BedrockCodec.Builder, codec: BedrockCodec): BedrockCodec.Builder =
                builder.apply {
                    deregisterPacket(ItemComponentPacket::class.java)
                    registerPacket({ ItemComponentPacket() }, ItemComponentSerializer_v419.INSTANCE, 162)
                }

            override fun getRequiredVersion(): Int = ProtocolVersion.latest().protocol // always support latest version
        })
    }
}
