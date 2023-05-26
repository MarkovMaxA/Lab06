package client

import client.network.UDPClient;
fun main() {
    val port=22837

    var console=ConsoleManager()
    try{
        var client = UDPClient(InetAdress.getLocalHost(), port)
        var cli = runner(client,console)
        cli.interactiveMode()
    }
    catch(e: Exception){
        println("Невозможно подключиться к серверу.")
    }
}