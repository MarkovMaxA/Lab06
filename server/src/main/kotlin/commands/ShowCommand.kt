package commands

import common.entities.MovieManager
import common.net.requests.Request
import common.net.requests.ShowRequest
import common.net.responses.Response
import common.net.responses.ResponseCode
import common.net.responses.ShowResponse

class ShowCommand(private val movieManager: MovieManager): Command() {
    /**
     * Get information about command abstract method
     *
     * @return information about command [String]
     * @author Markov Maxim 2023
     */
    override fun getDescription() = "Command is showing description of all elements in collection in console"

    /**
     * Get name of command abstract method
     *
     * @return name of command [String]
     * @author Markov Maxim 2023
     */
    override fun getName() = "show"

    /**
     * Execute command abstract method.
     *
     * @param request if it is needed [String]
     * @return none
     * @author Markov Maxim 2023
     */
    override fun execute(request: Request): Response {
        val req = request as? ShowRequest ?:
            return ShowResponse(ResponseCode.FAIL, null, "request cast error", null)

        return ShowResponse(ResponseCode.OK, "Movie set", null,
            movieManager.getMovieQueue().toList())
    }
}