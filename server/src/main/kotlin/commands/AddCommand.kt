package commands

import common.CommandID
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
     * @param request if it is needed [String]
     * @return none
     * @author Markov Maxim 2023
     */
    override fun execute(request: UniqueCommandRequest): UniqueCommandResponse {
        return try {
            val id = movieManager.getMovieQueue().size

            request.movie!!.setNewId(id.toLong() + 1)
            movieManager.addMovie(request.movie!!)
            UniqueCommandResponse(ResponseCode.OK, messageC = "Movie added to collection with id = $id",
                commandIDC = CommandID.ADD)
        } catch (e: Exception) {
            UniqueCommandResponse(ResponseCode.FAIL, exceptionDataC = e.toString(), commandIDC = CommandID.ADD)
        }
    }
}