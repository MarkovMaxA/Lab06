package common.net.responses

import common.CommandID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.annotations.Nullable

enum class ResponseCode {
    OK,
    FAIL
}

@Serializable
open class Response(val responseCode: ResponseCode,
                    @SerialName("message")
                    @Nullable
                    val message: String?,
                    @SerialName("exceptionData")
                    @Nullable
                    val exceptionData: String?,
                    val commandID: CommandID)