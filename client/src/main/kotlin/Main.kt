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

    var client = UDPClient(InetAddress.getLocalHost(), PORT)
    
    commandManager.addCommand(AddCommand(client))
    commandManager.addCommand(AddIfMaxCommand(client))
    commandManager.addCommand(AddIfMinCommand(client))
    commandManager.addCommand(RemoveByIdCommand(client))
    commandManager.addCommand(ExitCommand())
    commandManager.addCommand(HelpCommand(client))
    commandManager.addCommand(InfoCommand(client))
    commandManager.addCommand(MaxScreenwriterCommand(client))
    commandManager.addCommand(PrintAscendingCommand(client))
    commandManager.addCommand(PrintDescendingCommand(client))
    commandManager.addCommand(RemoveLowerCommand(client))
    commandManager.addCommand(ShowCommand(client))
    commandManager.addCommand(UpdateCommand(client))
    val runManager = RunManager(commandManager)

    runManager.run(commandManager)
}