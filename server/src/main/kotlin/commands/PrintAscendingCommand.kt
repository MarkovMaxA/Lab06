package commands

import common.entities.MovieManager
import common.net.requests.PrintAscendingRequest
import common.net.requests.Request
import common.net.responses.PrintAscendingResponse
import common.net.responses.Response
import common.net.responses.ResponseCode

class PrintAscendingCommand(private val movieManager: MovieManager): Command() {
    /**
     * Get information about command abstract method
     *
     * @return information about command [String]
     * @author Markov Maxim 2023
     */
    override fun getDescription() = "Command is printing all the elements ascending way"

    /**
     * Get name of command abstract method
     *
     * @return name of command [String]
     * @author Markov Maxim 2023
     */
    override fun getName() = "print_ascending"

    /**
     * Execute command abstract method.
     *
     * @param request if it is needed [String]
     * @return none
     * @author Markov Maxim 2023
     */
    override fun execute(request: Request): Response {
        val req = request as? PrintAscendingRequest ?:
            return PrintAscendingResponse(ResponseCode.FAIL, null, "request cast error", null)

        val movies = movieManager.getMovieQueue()
        val sortedMovies = movies.stream()
            .sorted(Comparator.comparingLong { it.getOscarsCount() ?: 0 })

        return PrintAscendingResponse(ResponseCode.OK, "Ascending sorted movies", null, sortedMovies.toList())
    }
}