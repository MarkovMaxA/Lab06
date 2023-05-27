package client.commands


import client.builders.MovieBuilder
import client.net.UDPClient
import common.net.requests.*
import common.net.responses.*
import common.*
class ShowCommand(val client: UDPClient): Command() {
    override fun getName() = "show"
    override fun execute(argument: String?): Request {
        if (argument != null) throw CommandArgumentException("Method show don't support arguments")
        val response = client.sendAndReceiveCommand(ShowRequest()) as AddResponse
        return ShowRequest()

    }
}