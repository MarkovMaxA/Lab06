package client

import client.net.UDPClient
import client.console.*
import client.commands.*
import commands.CommandManager
import client.run.*
import client.net.*
import java.net.*
fun main() {
    val commandManager = CommandManager()

    val PORT=22837

    commandManager.addCommand(AddCommand())
    commandManager.addCommand(AddIfMaxCommand())
    commandManager.addCommand(AddIfMinCommand())
    commandManager.addCommand(RemoveByIdCommand())
    commandManager.addCommand(ExitCommand())
    commandManager.addCommand(HelpCommand())
    commandManager.addCommand(InfoCommand())
    commandManager.addCommand(MaxScreenwriterCommand())
    commandManager.addCommand(PrintAscendingCommand())
    commandManager.addCommand(PrintDescendingCommand())
    commandManager.addCommand(RemoveLowerCommand())
    commandManager.addCommand(ShowCommand())
    commandManager.addCommand(UpdateCommand())
    val runManager = RunManager(commandManager)

    runManager.run(commandManager)
    try{
        var client = UDPClient(InetAddress.getLocalHost(), PORT)
    }
    catch(e: Exception){
        println("Невозможно подключиться к серверу.")
    }
}