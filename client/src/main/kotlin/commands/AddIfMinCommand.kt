package client.commands


import client.builders.MovieBuilder
import common.net.requests.*
import common.net.responses.*
import common.*
class AddIfMinCommand(): Command() {
    override fun getName() = "add_if_min"
    override fun execute(argument: String?): Request {
        if (argument != null) throw CommandArgumentException("Method add_if_min don't support arguments")
        val movie= MovieBuilder.build()
        return AddIfMinRequest(movie)

    }
}