package client.commands


import client.builders.MovieBuilder
import client.net.UDPClient
import common.net.requests.*
import common.net.responses.*
import common.*
class MaxScreenwriterCommand(val client: UDPClient): Command() {
    override fun getName() = "max_by_screenwriter"
    override fun execute(argument: String?): Request {
        if (argument != null) throw CommandArgumentException("Method max_screenwriter don't support arguments")
        val response = client.sendAndReceiveCommand(MaxScreenwriterRequest()) as AddResponse
        return MaxScreenwriterRequest()

    }
}