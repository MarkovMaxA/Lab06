package common.net.responses

import common.CommandID
import common.entities.Movie
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
class AddResponse(private val responseCodeC: ResponseCode,
                  private val messageC: String?,
                  private val exceptionDataC: String?): Response(responseCodeC, messageC, exceptionDataC, CommandID.ADD)

@Serializable
class AddIfMaxResponse(private val responseCodeC: ResponseCode,
                      private val messageC: String?,
                      private val exceptionDataC: String?): Response(responseCodeC, messageC, exceptionDataC, CommandID.ADDIFMAX)

@Serializable
class AddIfMinResponse(private val responseCodeC: ResponseCode,
                      private val messageC: String?,
                      private val exceptionDataC: String?): Response(responseCodeC, messageC, exceptionDataC, CommandID.ADDIFMIN)

@Serializable
class ExecuteScriptResponse(private val responseCodeC: ResponseCode,
                           private val messageC: String?,
                           private val exceptionDataC: String?): Response(responseCodeC, messageC, exceptionDataC, CommandID.EXECUTE_SCRIPT)

@Serializable
class HelpResponse(private val responseCodeC: ResponseCode,
                  private val messageC: String?,
                  private val exceptionDataC: String?): Response(responseCodeC, messageC, exceptionDataC, CommandID.HELP)

@Serializable
class InfoResponse(private val responseCodeC: ResponseCode,
                  private val messageC: String?,
                  private val exceptionDataC: String?): Response(responseCodeC, messageC, exceptionDataC, CommandID.INFO)

@Serializable
class MaxScreenwriterResponse(private val responseCodeC: ResponseCode,
                             private val messageC: String?,
                             private val exceptionDataC: String?): Response(responseCodeC, messageC, exceptionDataC, CommandID.MAX_SCREENWRITER)

@Serializable
class PrintAscendingResponse(private val responseCodeC: ResponseCode,
                            private val messageC: String?,
                            private val exceptionDataC: String?,
                            val hashSet: Set<Movie>): Response(responseCodeC, messageC, exceptionDataC, CommandID.PRINT_ASCENDING)

@Serializable
class PrintDescendingRequest(private val responseCodeC: ResponseCode,
                             private val messageC: String?,
                             private val exceptionDataC: String?,
                             val hashSet: Set<Movie>): Response(responseCodeC, messageC, exceptionDataC, CommandID.PRINT_DESCENDING)

@Serializable
class RemoveByIdRequest(private val responseCodeC: ResponseCode,
                        private val messageC: String?,
                        private val exceptionDataC: String?): Response(responseCodeC, messageC, exceptionDataC, CommandID.REMOVE_BY_ID)

@Serializable
class RemoveLowerRequest(private val responseCodeC: ResponseCode,
                         private val messageC: String?,
                         private val exceptionDataC: String?): Response(responseCodeC, messageC, exceptionDataC, CommandID.REMOVE_LOWER)

@Serializable
class ShowRequest(private val responseCodeC: ResponseCode,
                  private val messageC: String?,
                  private val exceptionDataC: String?,
                  val hashSet: Set<Movie>): Response(responseCodeC, messageC, exceptionDataC, CommandID.SHOW)

@Serializable
class UpdateByIdRequest(private val responseCodeC: ResponseCode,
                        private val messageC: String?,
                        private val exceptionDataC: String?): Response(responseCodeC, messageC, exceptionDataC, CommandID.UPDATE_BY_ID)