package client.run

import client.commands.Command
import client.console.ConsoleManager
import client.net.UDPClient
import commands.CommandManager
import common.net.responses.ResponseCode
import common.net.responses.UniqueCommandResponse

/**
 * Command execution code
 */
enum class ExecutionCode {
    COMPLETED,
    NO_COMMAND,
    EXCEPTION
}

/**
 * Progrum runtime representative class
 */
class RunManager(private val commandManager: CommandManager, private val client: UDPClient) {
    /**
     * run method
     *
     * @author Markov Maxim 2023S
     */
    fun run(commandManager: CommandManager) {
        while (ConsoleManager.hasNext()) {
            val line = ConsoleManager.getNextLine()

            val tokens = line.split(" ")

            val command = commandManager.getCommands()[tokens[0]]
            try {
                val executionResponse = if (tokens.size > 1) commandExecute(command!!, tokens[1])
                else commandExecute(command!!, null)
                println(executionResponse)
            } catch (e: Exception) {
                var executionCode = ExecutionCode.EXCEPTION
                ConsoleManager.consolePrint(e.javaClass.simpleName + "\n")
            }
        }
    }
//    fun runLine(line: String){
//        val tokens = line.split(" ")
//
//        if (tokens.isEmpty()) {
//            println("Something went wrong:(")
//        }
//        val command = commandManager.getCommands()[tokens[0]]
//
//        val executionCode = if (tokens.size > 1) commandExecution(command, tokens[1])
//        else commandExecution(command, null)
//
//        if (executionCode == ExecutionCode.EXCEPTION) println("Something went wrong:((")
//        else if (executionCode == ExecutionCode.NO_COMMAND) println("There's no command $tokens")
//
//    }
    /**
     * command execution method
     *
     * @argument command to execute [Command]
     * @author Markov Maxim 2023
     */
    private fun commandExecute(command: Command, argument: String?): String? {
        val response = command.execute(argument) as UniqueCommandResponse
        val responseCode = response.responseCode
        val message = response.message
        val exceptionData = response.exceptionData
        val commandID = response.commandID
        val hashSetMovie = response.hashSetMovie
        val hashSetLong = response.hashSetLong
        val movie=response.movie

        var rez: String?
        rez = message

        if (responseCode == ResponseCode.OK) {
            if (exceptionData == null) {
                if (hashSetMovie!=null){
                    rez+="\n"
                    for (item in hashSetMovie) {
                        rez += item
                        rez += "\n"
                    }
                }

                if (hashSetLong!=null){
                    rez+="\n"
                    for (item in hashSetLong) {
                        rez += item
                        rez += "\n"
                    }
                }
                if (movie!=null){
                    rez+="\n"+movie.toString()
                }
            } else {
                rez = exceptionData.toString()
            }
        } else {
            rez = responseCode.toString()
        }
        return rez
    }




}