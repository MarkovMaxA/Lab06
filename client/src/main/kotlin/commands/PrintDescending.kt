package client.commands


import client.builders.MovieBuilder
import client.net.UDPClient
import common.net.requests.*
import common.net.responses.*
import common.*
class PrintDescendingCommand(val client: UDPClient): Command() {
    override fun getName() = "print_field_descending_oscars_count"
    override fun execute(argument: String?): Request {
        if (argument != null) throw CommandArgumentException("Method print_ascending don't support arguments")
        val response = client.sendAndReceiveCommand(PrintDescendingRequest()) as AddResponse
        return PrintDescendingRequest()

    }
}