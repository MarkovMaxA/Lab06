package commands

import common.entities.MovieManager
import common.net.requests.*
import common.net.responses.*
import java.lang.Exception

class AddCommand(val movieManager: MovieManager): Command() {
    /**
     * Get information about command abstract method
     *
     * @return information about command [String]
     * @author Markov Maxim 2023
     */
    override fun getDescription() = "Command is adding new element in collections"

    /**
     * Get name of command abstract method
     *
     * @return name of command [String]
     * @author Markov Maxim 2023
     */
    override fun getName() = "add"

    /**
     * Execute command abstract method.
     *
     * @param argument if it is needed [String]
     * @return none
     * @author Markov Maxim 2023
     */
    override fun execute(request: Request): Response {
        val req = request as? AddRequest ?:
            return AddResponse(ResponseCode.FAIL, null, "request cast error")

        return try {
            val id = movieManager.getMovieQueue().size

            req.movie.setNewId(id.toLong() + 1)
            movieManager.addMovie(req.movie)
            AddResponse(ResponseCode.OK, "Movie added to collection with id = $id", null)
        } catch (e: Exception) {
            AddResponse(ResponseCode.FAIL, null, e.toString())
        }
    }
}