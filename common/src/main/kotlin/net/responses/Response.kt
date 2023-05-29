package common.net.responses

import common.CommandID
import java.io.Serializable

enum class ResponseCode {
    OK,
    FAIL
}

open class Response(val responseCode: ResponseCode,
                    val message: String?,
                    val exceptionData: String?,
                    val commandID: CommandID) : Serializable