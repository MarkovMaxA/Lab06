package client.commands


import client.builders.MovieBuilder
import client.net.UDPClient
import common.net.requests.*
import common.net.responses.*
import common.*
class PrintAscendingCommand(val client: UDPClient): Command() {
    override fun getName() = "print_ascending"
    override fun execute(argument: String?): Response {
        if (argument != null) throw CommandArgumentException("Method print_ascending don't support arguments")
        val response = client.sendAndReceiveCommand(UniqueCommandRequest(commandIDc = CommandID.PRINT_ASCENDING))
        return response

    }
}