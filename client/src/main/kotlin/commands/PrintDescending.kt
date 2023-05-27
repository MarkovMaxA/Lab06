package client.commands


import client.builders.MovieBuilder
import common.net.requests.*
import common.net.responses.*
import common.*
class PrintDescendingCommand(): Command() {
    override fun getName() = "print_field_descending_oscars_count"
    override fun execute(argument: String?): Request {
        if (argument != null) throw CommandArgumentException("Method print_ascending don't support arguments")
        return PrintDescendingRequest()

    }
}