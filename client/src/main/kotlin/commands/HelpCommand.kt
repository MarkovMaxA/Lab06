package client.commands


import client.builders.MovieBuilder
import common.net.requests.*
import common.net.responses.*
import common.*
class HelpCommand(): Command() {
    override fun getName() =  "help"
    override fun execute(argument: String?): Request {
        if (argument != null) throw CommandArgumentException("Method help don't support arguments")
        return HelpRequest()

    }
}