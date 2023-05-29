package client.net

import common.CommandID
import common.net.requests.*
import common.net.responses.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.commons.lang3.SerializationUtils
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.nio.*
import java.nio.channels.DatagramChannel
import java.util.Arrays

class UDPClient(address: InetAddress, private val port: Int) {
    private val PACKET_SIZE = 1024
    private val DATA_SIZE = PACKET_SIZE - 1
    private var client: DatagramChannel? = null
    private val address: InetSocketAddress

    init {
        this.address = InetSocketAddress(address, port)
    }
    fun SetConnection(){
        client = DatagramChannel.open().bind(null).connect(address)
        client!!.configureBlocking(false)

    }

    fun sendAndReceiveCommand(request: Request): Response {
        //val data = SerializationUtils.serialize(request)
        //val responseBytes = sendAndReceiveData(data)
        return  Response(ResponseCode.FAIL, null, null, CommandID.NONE)// SerializationUtils.deserialize(responseBytes)
    }

    private fun sendData(data: ByteArray) {
        val ret = Array(Math.ceil(data.size / DATA_SIZE.toDouble()).toInt()) {
            ByteArray(
                DATA_SIZE
            )
        }
        var start = 0
        for (i in ret.indices) {
            ret[i] = Arrays.copyOfRange(data, start, start + DATA_SIZE)
            start += DATA_SIZE
        }
        for (i in ret.indices) {
            val chunk = ret[i]
            if (i == ret.size - 1) {
                var lastChunk = chunk + byteArrayOf(1)
                client!!.send(ByteBuffer.wrap(lastChunk), address)
            } else {
                val answer = chunk + byteArrayOf(0)
                client!!.send(ByteBuffer.wrap(answer), address)
            }
        }

    }


    private fun receiveData(): ByteArray {
        var received = false
        var result = ByteArray(0)
        while (!received) {
            val data = receiveData(PACKET_SIZE)
            if (data[data.size - 1].toInt() == 1) {
                received = true
            }
            result = Arrays.copyOf(data, data.size - 1)
        }
        return result
    }

    private fun receiveData(bufferSize: Int): ByteArray {
        val buffer = ByteBuffer.allocate(bufferSize)
        var address: SocketAddress? = null
        while (address == null) {
            address = client!!.receive(buffer)
        }
        return buffer.array()
    }
    private fun sendAndReceiveData(data: ByteArray): ByteArray {
        sendData(data)
        return receiveData()
    }

}