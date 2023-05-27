package commands

import common.entities.MovieManager
import common.net.requests.Request
import common.net.requests.UpdateByIdRequest
import common.net.responses.Response
import common.net.responses.ResponseCode
import common.net.responses.UpdateByIdResponse

class UpdateCommand(private val movieManager: MovieManager): Command() {
    /**
     * Get information about command abstract method
     *
     * @return information about command [String]
     * @author Markov Maxim 2023
     */
    override fun getDescription() = "Command is updating element from collection"

    /**
     * Get name of command abstract method
     *
     * @return name of command [String]
     * @author Markov Maxim 2023
     */
    override fun getName() = "update"

    /**
     * Execute command abstract method.
     *
     * @param request if it is needed [String]
     * @return none
     * @author Markov Maxim 2023
     */
    override fun execute(request: Request): Response {
        val req = request as? UpdateByIdRequest ?:
            return UpdateByIdResponse(ResponseCode.FAIL, null, "request cast error")

        movieManager.removeElementById(req.movie.getId())

        return if (movieManager.removeElementById(req.movie.getId())) {
            movieManager.addMovie(req.movie)
            UpdateByIdResponse(ResponseCode.OK, "Element was updated", null)
        }
        else UpdateByIdResponse(ResponseCode.FAIL, null, "Element wasn't updated")
    }
}