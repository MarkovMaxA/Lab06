package net

import commands.CommandManager
import common.net.requests.Request
import common.net.responses.Response
import org.apache.commons.lang3.ArrayUtils
import org.apache.commons.lang3.SerializationUtils
import org.slf4j.LoggerFactory
import java.net.InetSocketAddress
import java.net.SocketAddress

abstract class UDP(var address: InetSocketAddress, val commandManager: CommandManager) {
    protected val logger = LoggerFactory.getLogger(UDP::class.java)
    protected var runFlag = true

    fun getAddr() = address

    abstract fun receive(): Pair<Array<Byte>, SocketAddress?>

    abstract fun send(data: ByteArray, address: SocketAddress)

    abstract fun connect(address: SocketAddress)

    abstract fun disconnect()

    abstract fun close()

    fun stop() {
        runFlag = !runFlag
    }

    fun run() {
        logger.info("Server started, address=$address")

        while (runFlag) {
            var data: Pair<Array<Byte>, SocketAddress?>

            try {
                data = receive()
                if (data.second == null) {
                    logger.error("Receiving data error")
                    disconnect()
                    continue
                }
            } catch (e: Exception) {
                logger.error("Receiving data error ${e.toString()}", e)
                disconnect()
                continue
            }

            try {
                connect(data.second!!)
                logger.info("Server connected to client address=${data.second}")
            } catch (e: Exception) {
                logger.error("Client connection error $e", e)
            }

            val request: Request

            try {
                request = SerializationUtils.deserialize(ArrayUtils.toPrimitive(data.first))
                logger.info("Request $request handle")
            } catch (e: Exception) {
                logger.error("Unnable to serialize request $e", e)
                disconnect()
                continue
            }

            val response: Response

            try {
                response = commandManager.handle(request)
                logger.info("Created response $response")
            } catch (e: Exception) {
                logger.error("Command error $e", e)
            }

            disconnect()
            logger.info("Сервер отключен")
        }

        close()
    }
}