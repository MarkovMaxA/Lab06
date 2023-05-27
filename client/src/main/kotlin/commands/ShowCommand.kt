package client.commands


import client.builders.MovieBuilder
import common.net.requests.*
import common.net.responses.*
import common.*
class ShowCommand(): Command() {
    override fun getName() = "show"
    override fun execute(argument: String?): Request {
        if (argument != null) throw CommandArgumentException("Method show don't support arguments")
        return ShowRequest()

    }
}