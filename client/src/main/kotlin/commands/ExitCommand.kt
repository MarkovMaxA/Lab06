package client.commands

import common.CommandArgumentException
import common.net.requests.HelpRequest
import common.net.requests.Request
import kotlin.system.exitProcess
class ExitCommand(): Command() {
    override fun getName() =  "exit"
    override fun execute(argument: String?): Request {
        if (argument != null) throw CommandArgumentException("Method exit don't support arguments")

        exitProcess(0)
    }
}