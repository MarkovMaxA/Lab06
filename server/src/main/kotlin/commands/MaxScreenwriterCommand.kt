package commands

import common.entities.Movie
import common.entities.MovieManager
import common.net.requests.MaxScreenwriterRequest
import common.net.requests.Request
import common.net.responses.MaxScreenwriterResponse
import common.net.responses.Response
import common.net.responses.ResponseCode
import java.util.*
import kotlin.Comparator

class MaxScreenwriterCommand(private val movieManager: MovieManager): Command() {
    /**
     * Get information about command abstract method
     *
     * @return information about command [String]
     * @author Berman Denis 2023
     */
    override fun getDescription() = "Command is printing all the elements, which value of screenwriter is maximum"

    /**
     * Get name of command abstract method
     *
     * @return name of command [String]
     * @author Berman Denis 2023
     */
    override fun getName() = "max_by_screenwriter"

    /**
     * Execute command abstract method.
     *
     * @param argument if it is needed [String]
     * @return none
     * @author Berman Denis 2023
     */
    override fun execute(request: Request): Response {
        val req = request as? MaxScreenwriterRequest ?:
            return MaxScreenwriterResponse(ResponseCode.FAIL, null, "request cast error", null)

        val movies = movieManager.getMovieQueue()
        var maxMovie: Movie? = movieManager.getMovieQueue().stream()
            .max(Comparator.comparingInt { it.getScreenwriter().getHeight() })
            .orElse(null)


        return if (maxMovie != null) MaxScreenwriterResponse(ResponseCode.OK, "Found maxscreenwriter heigh movie",
            null, maxMovie)
        else MaxScreenwriterResponse(ResponseCode.FAIL, "There's is no movie with maxscreenwriter",
            null, null)
    }
}