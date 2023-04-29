package dev.minjae.customitemloader.wdpe.handler

import dev.waterdog.waterdogpe.network.PacketDirection
import dev.waterdog.waterdogpe.network.protocol.handler.PluginPacketHandler
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket
import org.cloudburstmc.protocol.bedrock.packet.ItemComponentPacket
import org.cloudburstmc.protocol.common.PacketSignal

class DownstreamPacketHandler : PluginPacketHandler {

    private var itemComponentPacketSent = false

    override fun handlePacket(p0: BedrockPacket, p1: PacketDirection): PacketSignal {
        if (p0 is ItemComponentPacket && p1 == PacketDirection.FROM_SERVER) {
            if (itemComponentPacketSent) {
                return PacketSignal.UNHANDLED
            }
            itemComponentPacketSent = true
            return PacketSignal.HANDLED
        }
        return PacketSignal.UNHANDLED
    }
}
