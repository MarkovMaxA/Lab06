import common.entities.MovieManager
import net.*
import java.util.Scanner

class ServerController(private val movieManager: MovieManager, private val server: UDP) {

    private val scanner: Scanner = Scanner(System.`in`)
    fun run() {
        while (scanner.hasNext()) {
            val line = scanner.nextLine()

            println(line)
            if (line == "exit") {
                server.stop()
                return
            }
            if (line == "save")
                movieManager.save()
        }
    }
}