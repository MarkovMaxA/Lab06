package client.commands


import client.builders.MovieBuilder
import common.net.requests.*
import common.net.responses.*
import common.*
class AddIfMaxCommand(): Command() {
    override fun getName() = "add_if_max"
    override fun execute(argument: String?): Request {
        if (argument != null) throw CommandArgumentException("Method add_if_max don't support arguments")
        val movie= MovieBuilder.build()
        return AddIfMaxRequest(movie)

    }
}