package commands

import common.CommandID
import common.net.requests.Request
import common.net.responses.Response
import common.net.responses.ResponseCode

class CommandManager {
    private val commands = HashMap<CommandID, Command>() // map of commands

    /**
     * get map of commands method
     *
     * @return only readable map of commands [HashMap]
     * @author Markov Maxim 2023
     */
    fun getCommands() = commands.toMap()

    /**
     * add command to hashmap function
     *
     * @return true if command was added
     * @author Markov Maxim 2023
     */
    fun addCommand(command: Command, commandID: CommandID): Boolean {
        commands[commandID] = command
        return true
    }

    /**
     * handle command method
     *
     * @argument request request to handle [Request]
     * @return response to send [Response]
     * @author Markov Maxim 2023
     */
    fun handle(request: Request): Response {
        val command = commands[request.commandID] ?:
            return Response(ResponseCode.FAIL, "", "No such command", CommandID.NONE)
        return command.execute(request)
    }
}