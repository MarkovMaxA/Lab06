package client.commands


import client.builders.MovieBuilder
import client.net.UDPClient
import common.net.requests.*
import common.net.responses.*
import common.*
class AddIfMaxCommand(val client: UDPClient): Command() {
    override fun getName() = "add_if_max"
    override fun execute(argument: String?): Request {
        if (argument != null) throw CommandArgumentException("Method add_if_max don't support arguments")
        val movie= MovieBuilder.build()
        val response = client.sendAndReceiveCommand(AddIfMaxRequest(movie)) as AddResponse
        return AddIfMaxRequest(movie)

    }
}