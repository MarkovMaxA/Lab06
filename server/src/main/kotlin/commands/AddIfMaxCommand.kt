package commands

import common.entities.Movie
import common.entities.MovieManager
import common.net.requests.AddIfMaxRequest
import common.net.requests.Request
import common.net.responses.AddIfMaxResponse
import common.net.responses.Response
import common.net.responses.ResponseCode
import java.lang.Exception

class AddIfMaxCommand(private val movieManager: MovieManager): Command() {
    /**
     * Get information about command abstract method
     *
     * @return information about command [String]
     * @author Markov Maxim 2023
     */
    override fun getDescription() = "Command is adding element, if it's value more then maximum"

    /**
     * Get name of command abstract method
     *
     * @return name of command [String]
     * @author Markov Maxim 2023
     */
    override fun getName() = "add_if_max"

    /**
     * Execute command abstract method.
     *
     * @param request if it is needed [String]
     * @return none
     * @author Markov Maxim 2023
     */
    override fun execute(request: Request): Response {
        val req = request as? AddIfMaxRequest ?:
        return AddIfMaxResponse(ResponseCode.FAIL, null, "request cast error")

        return try {
            val maxCount = movieManager.getMovieQueue().stream()
                .map(Movie::getOscarsCount)
                .mapToLong { it ?: -1 }
                .max()
                .orElse(-1)

            val id = movieManager.getMovieQueue().size
            req.movie.setNewId(id.toLong() + 1)

            if ((req.movie.getOscarsCount() ?: -1) > maxCount) {
                movieManager.addMovie(req.movie)
                AddIfMaxResponse(ResponseCode.OK, "Movie added to collection with id = $id", null)
            } else {
                AddIfMaxResponse(ResponseCode.FAIL, null, "Movie oscars count isn't max. " +
                        "Max value is $maxCount")
            }
        } catch (e: Exception) {
            AddIfMaxResponse(ResponseCode.FAIL, null, e.toString())
        }
    }
}