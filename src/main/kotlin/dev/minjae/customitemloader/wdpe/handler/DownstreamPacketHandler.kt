package dev.minjae.customitemloader.wdpe.handler

import com.nukkitx.protocol.bedrock.BedrockSession
import com.nukkitx.protocol.bedrock.packet.ItemComponentPacket
import dev.waterdog.waterdogpe.utils.exceptions.CancelSignalException
import dev.waterdog.waterdogpe.utils.types.PacketHandler

class DownstreamPacketHandler(session: BedrockSession) : PacketHandler(session) {

    private var itemComponentPacketSent = false

    override fun handle(packet: ItemComponentPacket): Boolean {
        if (itemComponentPacketSent) {
            throw CancelSignalException.CANCEL
        }
        itemComponentPacketSent = true
        return true
    }
}
