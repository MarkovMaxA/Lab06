package commands

import common.entities.Movie
import common.entities.MovieManager
import common.net.requests.PrintDescendingRequest
import common.net.requests.Request
import common.net.responses.PrintDescendingResponse
import common.net.responses.Response
import common.net.responses.ResponseCode

class PrintDescendingCommand(private val movieManager: MovieManager): Command() {
    /**
     * Get information about command abstract method
     *
     * @return information about command [String]
     * @author Markov Maxim 2023
     */
    override fun getDescription() = "Command is printing all the elements descending way"

    /**
     * Get name of command abstract method
     *
     * @return name of command [String]
     * @author Markov Maxim 2023
     */
    override fun getName() = "print_descending"

    /**
     * Execute command abstract method.
     *
     * @param request if it is needed [String]
     * @return none
     * @author Markov Maxim 2023
     */
    override fun execute(request: Request): Response {
        val req = request as? PrintDescendingRequest ?:
            return PrintDescendingResponse(ResponseCode.FAIL, null, "request cast error", null)

        val movies = movieManager.getMovieQueue()
        val sortedOscars = movies.stream()
            .map(Movie::getOscarsCount)
            .sorted(Comparator.comparingLong { (it ?: 0) * -1 })

        return PrintDescendingResponse(ResponseCode.OK, "Descending sorted movies", null, sortedOscars.toList())
    }
}