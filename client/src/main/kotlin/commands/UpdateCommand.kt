package client.commands


import client.builders.MovieBuilder
import client.net.UDPClient
import common.net.requests.*
import common.*
class UpdateCommand(val client: UDPClient): Command() {
    override fun getName() = "update"
    override fun execute(argument: String?): Request {
        if (argument == null) throw CommandArgumentException("Method remove_by_id don't support zero arguments")

        val id = argument.toLong()
        val movie= MovieBuilder.build()
        return UpdateByIdRequest(id,movie)
    }
}