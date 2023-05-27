package commands

import common.net.requests.HelpRequest
import common.net.requests.Request
import common.net.responses.HelpResponse
import common.net.responses.Response
import common.net.responses.ResponseCode

class HelpCommand(private val commandManager: CommandManager): Command() {
    /**
     * Get information about command abstract method
     *
     * @return information about command [String]
     * @author Markov Maxim 2023
     */
    override fun getDescription() = "Command is printing commands description in console"

    /**
     * Get name of command abstract method
     *
     * @return name of command [String]
     * @author Markov Maxim 2023
     */
    override fun getName() =  "help"

    /**
     * Execute command abstract method.
     *
     * @param argument if it is needed [String]
     * @return none
     * @author Markov Maxim 2023
     */
    override fun execute(request: Request): Response {
        val req = request as? HelpRequest ?:
        return HelpResponse(ResponseCode.FAIL, null, "request cast error")

        val commands = commandManager.getCommands()

        val outString = StringBuilder()

        commandManager.getCommands().values.forEach { outString.append("${it.getName()} - ${it.getDescription()}\n\n")}
        return HelpResponse(ResponseCode.OK, outString.toString(), null)
    }
}