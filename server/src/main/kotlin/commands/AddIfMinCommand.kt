package commands

import common.entities.Movie
import common.entities.MovieManager
import common.net.requests.*
import common.net.responses.*
import java.lang.Exception

class AddIfMinCommand(private val movieManager: MovieManager): Command() {
    /**
     * Get information about command abstract method
     *
     * @return information about command [String]
     * @author Berman Denis 2023
     */
    override fun getDescription() = "Command is adding element, if it's value less then minimum"

    /**
     * Get name of command abstract method
     *
     * @return name of command [String]
     * @author Berman Denis 2023
     */
    override fun getName() = "add_if_min"

    /**
     * Execute command abstract method.
     *
     * @param argument if it is needed [String]
     * @return none
     * @author Berman Denis 2023
     */
    override fun execute(request: Request): Response {
        val req = request as? AddIfMinRequest ?:
        return AddIfMaxResponse(ResponseCode.FAIL, null, "request cast error")

        return try {
            val minCount = movieManager.getMovieQueue().stream()
                .map(Movie::getOscarsCount)
                .mapToLong { it ?: -1 }
                .min()
                .orElse(Long.MAX_VALUE)

            val id = movieManager.getMovieQueue().size
            req.movie.setNewId(id.toLong() + 1)

            if ((req.movie.getOscarsCount() ?: -1) < minCount) {
                movieManager.addMovie(req.movie)
                AddIfMinResponse(ResponseCode.OK, "Movie added to collection with id = $id", null)
            } else {
                AddIfMinResponse(ResponseCode.FAIL, null, "Movie oscars count isn't min. " +
                        "Min value is $minCount")
            }
        } catch (e: Exception) {
            AddIfMaxResponse(ResponseCode.FAIL, null, e.toString())
        }
    }
}