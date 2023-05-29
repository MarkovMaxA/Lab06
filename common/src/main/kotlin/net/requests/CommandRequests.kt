package common.net.requests

import common.CommandID
import common.entities.Movie
import kotlinx.serialization.Serializable

@Serializable
data class AddRequest(val movie: Movie): Request(CommandID.ADD)

class AddIfMaxRequest(val movie: Movie): Request(CommandID.ADDIFMAX)

class AddIfMinRequest(val movie: Movie): Request(CommandID.ADDIFMIN)

class ExecuteScriptRequest(val scriptName: String): Request(CommandID.EXECUTE_SCRIPT)

class HelpRequest(): Request(CommandID.HELP)

class InfoRequest(): Request(CommandID.INFO)

class MaxScreenwriterRequest(): Request(CommandID.MAX_SCREENWRITER)

class PrintAscendingRequest(): Request(CommandID.PRINT_ASCENDING)

class PrintDescendingRequest(): Request(CommandID.PRINT_DESCENDING)

class RemoveByIdRequest(val id: Long): Request(CommandID.REMOVE_BY_ID)

class RemoveLowerRequest(val oscarsCount: Long): Request(CommandID.REMOVE_LOWER)

class ShowRequest(): Request(CommandID.SHOW)

class UpdateByIdRequest(val id: Long, val movie:Movie): Request(CommandID.UPDATE_BY_ID)