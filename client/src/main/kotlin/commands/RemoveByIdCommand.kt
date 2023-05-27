package client.commands


import client.builders.MovieBuilder
import common.net.requests.*
import common.net.responses.*
import common.*
class RemoveByIdCommand(): Command() {
    override fun getName() = "remove_by_id"
    override fun execute(argument: String?): Request {
        if (argument == null) throw CommandArgumentException("Method remove_by_id don't support zero arguments")

        val id = argument.toLong()
        return RemoveByIdRequest(id)
    }
}