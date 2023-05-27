package net

import commands.CommandManager
import org.apache.commons.lang3.ArrayUtils
import java.net.*
import java.nio.ByteBuffer
import java.util.*

class UDPServerDatagram(address: InetAddress, commandManager: CommandManager, val port: Int):
    UDP(InetSocketAddress(address, port), commandManager) {
    val PACKET_SIZE
        get() = 1024
    val DATA_SIZE
        get() = PACKET_SIZE - 1

    val datagramSocket: DatagramSocket

    init {
        datagramSocket = DatagramSocket(getAddr())
        datagramSocket.reuseAddress = true
    }

    override fun receive(): Pair<Array<Byte>, SocketAddress?> {
        var receiveFlag = false
        var result = ByteArray(DATA_SIZE)
        var socketAddress: SocketAddress? = null

        while (!receiveFlag) {
            val data = ByteArray(PACKET_SIZE)
            val datagramPacket = DatagramPacket(data, PACKET_SIZE);

            datagramSocket.receive(datagramPacket)
            socketAddress = InetSocketAddress(datagramPacket.address, datagramPacket.port)

            if (data[PACKET_SIZE - 1].toInt() == 1) {
                receiveFlag = true
                logger.info("Data received from ${datagramPacket.address}")
            }
            result = data.copyOf(data.size - 1)
        }
        return Pair(ArrayUtils.toObject(result), socketAddress)
    }

    override fun send(data: ByteArray, address: SocketAddress) {
        var dataSend = Array(Math.ceil(data.size / DATA_SIZE.toDouble()).toInt()) {ByteArray(DATA_SIZE)}

        var start = 0
        for (i in 0 until dataSend.size) {
            dataSend[i] = Arrays.copyOfRange(data, start, start + DATA_SIZE)
            start += DATA_SIZE
        }

        for (i in 0 until dataSend.size) {
            if (i != dataSend.size - 1) {
                val datagramPacket = DatagramPacket(ByteBuffer.allocate(PACKET_SIZE).put(dataSend[i]).array(), PACKET_SIZE, address)
                datagramSocket.send(datagramPacket)
            } else {
                var last = dataSend[i] + (1).toByte()
                val datagramPacket = DatagramPacket(last, PACKET_SIZE, address)
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