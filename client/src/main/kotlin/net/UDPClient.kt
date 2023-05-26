package client.net

import java.net.InetAddress
import java.net.InetSocketAddress
import java.nio.channels.DatagramChannel

class UDPClient {
    private val PACKET_SIZE=1024
    private val DATA_SIZE=1

    fun setConnection(address: InetAddress, port: int){
        this.addr=InetSocketAddress(address,port)
        this.client=DatagramChannel.open().bind(null).connect(addr)
        this.client=configureBlocking(false)

    }
}