package common.net.requests

import common.CommandID
import kotlinx.serialization.Serializable

@Serializable
abstract class Request(val commandID: CommandID) : java.io.Serializable