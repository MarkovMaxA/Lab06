package client.commands


import client.builders.MovieBuilder
import common.net.requests.*
import common.net.responses.*
import common.*
class InfoCommand(): Command() {
    override fun getName() =  "info"
    override fun execute(argument: String?): Request {
        if (argument != null) throw CommandArgumentException("Method info don't support arguments")
        return InfoRequest()

    }
}