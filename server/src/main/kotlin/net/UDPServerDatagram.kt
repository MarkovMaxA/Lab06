package net

import commands.CommandManager
import java.net.*
import java.nio.ByteBuffer
import kotlin.math.ceil

class UDPServerDatagram(address: InetAddress, commandManager: CommandManager, port: Int):
    UDP(InetSocketAddress(address, port), commandManager) {
    private val PACKET_SIZE
        get() = 1024
    private val DATA_SIZE
        get() = PACKET_SIZE - 1

    private val datagramSocket: DatagramSocket = DatagramSocket(getAddr())

    init {
        datagramSocket.reuseAddress = true
    }

    override fun receive(): Pair<ByteArray, SocketAddress?> {
        var receiveFlag = false
        var result = ByteArray(DATA_SIZE)
        var socketAddress: SocketAddress? = null

        while (!receiveFlag) {
            val data = ByteArray(PACKET_SIZE)
            val datagramPacket = DatagramPacket(data, PACKET_SIZE)

            datagramSocket.receive(datagramPacket)
            socketAddress = InetSocketAddress(datagramPacket.address, datagramPacket.port)

            if (data[PACKET_SIZE - 1].toInt() == 1) {
                receiveFlag = true
                logger.info("Data received from ${datagramPacket.address}")
            }
            result = data.copyOf(data.size - 1)
        }
        return Pair(result, socketAddress)
    }

    override fun send(data: ByteArray, address: SocketAddress) {
        val dataSend = Array(ceil(data.size / DATA_SIZE.toDouble()).toInt()) {ByteArray(DATA_SIZE)}

        var start = 0
        for (i in dataSend.indices) {
            dataSend[i] = data.copyOfRange(start, start + PACKET_SIZE)
            start += DATA_SIZE
        }

        for (i in dataSend.indices) {
            if (i != dataSend.size - 1) {
                val datagramPacket = DatagramPacket(ByteBuffer.allocate(PACKET_SIZE).put(dataSend[i]).array(), PACKET_SIZE, address)
                datagramSocket.send(datagramPacket)
            } else {
                val datagramPacket = DatagramPacket(dataSend[i] + (1).toByte(), PACKET_SIZE, address)
                datagramSocket.send(datagramPacket)
            }
        }

        logger.info("Send ended")
    }

    override fun connect(address: SocketAddress) {
        datagramSocket.connect(address)
    }

    override fun disconnect() {
        datagramSocket.disconnect()
    }

    override fun close() {
        datagramSocket.close()
    }
}