package client.commands

import client.builders.MovieBuilder
import common.net.requests.*
import common.net.responses.*
import common.*

class AddCommand(): Command() {
    override fun getName() = "add"


    /**
     * Sending response method
     *
     * @param argument if it is needed [String]
     * @return none
     * @author Berman Denis
     */
    override fun execute(argument: String?): Request {
        if (argument != null) throw CommandArgumentException("Method add don't support arguments")
        val movie= MovieBuilder.build()
        return AddRequest(movie)
    }
}