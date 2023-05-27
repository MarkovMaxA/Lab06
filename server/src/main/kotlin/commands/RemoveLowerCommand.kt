package commands

import common.entities.Movie
import common.entities.MovieManager
import common.net.requests.RemoveLowerRequest
import common.net.requests.Request
import common.net.responses.RemoveLowerResponse
import common.net.responses.Response
import common.net.responses.ResponseCode

class RemoveLowerCommand(private val movieManager: MovieManager): Command() {
    /**
     * Get information about command abstract method
     *
     * @return information about command [String]
     * @author Berman Denis 2023
     */
    override fun getDescription() = "Command is removing all the elements, that are less"

    /**
     * Get name of command abstract method
     *
     * @return name of command [String]
     * @author Berman Denis 2023
     */
    override fun getName() = "remove_lower"

    /**
     * Execute command abstract method.
     *
     * @param argument if it is needed [String]
     * @return none
     * @author Berman Denis 2023
     */
    override fun execute(request: Request): Response {
        val req = request as? RemoveLowerRequest ?:
            return RemoveLowerResponse(ResponseCode.FAIL, null, "request cast error")


        val oscarsCount = req.oscarsCount

        val movieList = movieManager.getMovieQueue().stream()
            .filter {(it.getOscarsCount() ?: 0) < oscarsCount}
            .map(Movie::getId)

        movieList.forEach{movieManager.removeElementById(it)}

        return RemoveLowerResponse(ResponseCode.OK,
            "All movies with oscars count value less than ${req.oscarsCount} were removed",
            null)
    }
}