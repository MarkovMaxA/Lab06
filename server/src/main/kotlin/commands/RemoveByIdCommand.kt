package commands

import common.entities.MovieManager
import common.net.requests.RemoveByIdRequest
import common.net.requests.Request
import common.net.responses.RemoveByIdResponse
import common.net.responses.Response
import common.net.responses.ResponseCode

class RemoveByIdCommand(private val movieManager: MovieManager): Command() {
    /**
     * Get information about command abstract method
     *
     * @return information about command [String]
     * @author Markov Maxim 2023
     */
    override fun getDescription() = "Command is deleting element from collection"

    /**
     * Get name of command abstract method
     *
     * @return name of command [String]
     * @author Markov Maxim 2023
     */
    override fun getName() = "remove_by_id"

    /**
     * Execute command abstract method.
     *
     * @param request if it is needed [String]
     * @return none
     * @author Markov Maxim 2023
     */
    override fun execute(request: Request): Response {
        val req = request as? RemoveByIdRequest ?:
            return RemoveByIdResponse(ResponseCode.FAIL, null, "request cast error")

        return if (movieManager.removeElementById(req.id))
            RemoveByIdResponse(ResponseCode.OK,
                "Element with id = ${req.id} was removed", null)
        else RemoveByIdResponse(ResponseCode.FAIL, null,
            "Element with id = ${req.id} wasn't removed")
    }
}