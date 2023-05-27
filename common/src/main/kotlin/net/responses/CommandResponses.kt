package common.net.responses

import common.CommandID
import common.entities.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.annotations.Nullable

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
                              private val exceptionDataC: String?,
                              @SerialName("movie")
                              @Nullable
                              val movie: Movie?): Response(responseCodeC, messageC, exceptionDataC, CommandID.MAX_SCREENWRITER)

@Serializable
class PrintAscendingResponse(private val responseCodeC: ResponseCode,
                             private val messageC: String?,
                             private val exceptionDataC: String?,
                             @SerialName("hashSet")
                             @Nullable
                             val hashSet: List<Movie>?): Response(responseCodeC, messageC, exceptionDataC, CommandID.PRINT_ASCENDING)

@Serializable
class PrintDescendingResponse(private val responseCodeC: ResponseCode,
                              private val messageC: String?,
                              private val exceptionDataC: String?,
                              @SerialName("hashSet")
                              @Nullable
                              val hashSet: List<Long?>?
): Response(responseCodeC, messageC, exceptionDataC, CommandID.PRINT_DESCENDING)

@Serializable
class RemoveByIdResponse(private val responseCodeC: ResponseCode,
                         private val messageC: String?,
                         private val exceptionDataC: String?): Response(responseCodeC, messageC, exceptionDataC, CommandID.REMOVE_BY_ID)

@Serializable
class RemoveLowerResponse(private val responseCodeC: ResponseCode,
                          private val messageC: String?,
                          private val exceptionDataC: String?): Response(responseCodeC, messageC, exceptionDataC, CommandID.REMOVE_LOWER)

@Serializable
class ShowResponse(private val responseCodeC: ResponseCode,
                   private val messageC: String?,
                   private val exceptionDataC: String?,
                   @SerialName("hashSet")
                   @Nullable
                   val hashSet: List<Movie>?): Response(responseCodeC, messageC, exceptionDataC, CommandID.SHOW)

@Serializable
class UpdateByIdResponse(private val responseCodeC: ResponseCode,
                         private val messageC: String?,
                         private val exceptionDataC: String?): Response(responseCodeC, messageC, exceptionDataC, CommandID.UPDATE_BY_ID)