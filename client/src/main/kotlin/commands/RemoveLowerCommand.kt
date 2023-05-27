package client.commands


import client.builders.MovieBuilder
import client.net.UDPClient
import common.net.requests.*
import common.net.responses.*
import common.*
class RemoveLowerCommand(val client: UDPClient): Command() {
    override fun getName() = "remove_lower"
    override fun execute(argument: String?): Request {
        if (argument == null) throw CommandArgumentException("Method remove_by_id don't support zero arguments")

        val id = argument.toLong()
        val response = client.sendAndReceiveCommand(RemoveLowerRequest(id)) as AddResponse
        return RemoveLowerRequest(id)
    }
}