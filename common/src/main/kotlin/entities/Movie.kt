package common.entities

import common.EmptyStringException
import common.ValueLessThanZeroException
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.jetbrains.annotations.Nullable
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.max

/**
 * Movie genres enum representative class
 */
enum class MovieGenre {
    ACTION,
    COMEDY,
    TRAGEDY,
    FANTASY
}

/**
 * Mpaa ratings enum representative class
 */
enum class MpaaRating {
    G,
    PG,
    PG_13,
    R,
    NC_17
}

class LocalDateSerializer : KSerializer<LocalDate> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDate) {
        val result = value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        encoder.encodeString(result)
    }

    override fun deserialize(decoder: Decoder): LocalDate {
        return LocalDate.parse(decoder.decodeString())
    }
}

@Serializable
class Movie {
    private var name: String
    private var coordinates: Coordinates
    @SerialName("oscarsCount")
    @Nullable
    private var oscarsCount: Long?
    private var length: Int
    private var genre: MovieGenre
    @SerialName("mpaaRating")
    @Nullable
    private var mpaaRating: MpaaRating?
    private var screenWriter: Person
    private var id: Long
    @Serializable(with = LocalDateSerializer::class)
    private var creationDate: LocalDate

    constructor(name: String, coordinates: Coordinates,
                oscarsCount: Long?, length: Int,
                genre: MovieGenre, mpaaRating: MpaaRating?,
                screenWriter: Person, id: Long, date: LocalDate) {
        checkNameRestrictions(name)
        checkOscarsCountRestrictions(oscarsCount)
        checkLengthRestrictions(length)

        this.name = name
        this.coordinates = coordinates
        this.oscarsCount = oscarsCount
        this.length = length
        this.genre = genre
        this.mpaaRating = mpaaRating
        this.screenWriter = screenWriter

        this.id = id
        cntId = max(id, cntId)
        this.creationDate = date
    }
    constructor(name: String, coordinates: Coordinates,
                oscarsCount: Long?, length: Int,
                genre: MovieGenre, mpaaRating: MpaaRating?,
                screenWriter: Person, id: Long) {
        checkNameRestrictions(name)
        checkOscarsCountRestrictions(oscarsCount)
        checkLengthRestrictions(length)

        this.name = name
        this.coordinates = coordinates
        this.oscarsCount = oscarsCount
        this.length = length
        this.genre = genre
        this.mpaaRating = mpaaRating
        this.screenWriter = screenWriter

        this.id = id

        cntId = max(id, cntId)
        this.creationDate = LocalDate.now()
    }

    constructor(name: String, coordinates: Coordinates,
                oscarsCount: Long?, length: Int,
                genre: MovieGenre, mpaaRating: MpaaRating?,
                screenWriter: Person) {
        checkNameRestrictions(name)
        checkOscarsCountRestrictions(oscarsCount)
        checkLengthRestrictions(length)

        this.name = name
        this.coordinates = coordinates
        this.oscarsCount = oscarsCount
        this.length = length
        this.genre = genre
        this.mpaaRating = mpaaRating
        this.screenWriter = screenWriter
        id = giveId()
        creationDate = LocalDate.now()

    }

    companion object {
        fun checkNameRestrictions(name: String) {
            if (name.isEmpty()) throw EmptyStringException("Movie name can't be empty")
            if (name.contains(",")) throw EmptyStringException("Movie name can't use symbol ','")
        }

        fun checkOscarsCountRestrictions(oscarsCount: Long?) {
            if (oscarsCount != null)
                if (oscarsCount <= 0)
                    throw ValueLessThanZeroException("Oscars count needs to be more than zero")
        }

        fun checkLengthRestrictions(length: Int) {
            if (length <= 0) throw ValueLessThanZeroException("Length needs to be more than zero")
        }

        private var cntId: Long = 0

        /**
         * Giving id to movie instance method
         *
         * @return id of new movie [Long]
         */
        @JvmStatic
        private fun giveId(): Long {
            cntId += 1
            return cntId
        }
    }

    fun setNewId(id: Long) {
        this.id = id
    }

    /**
     * Name getter method
     *
     * @return movie name [String]
     * @author Markov Maxim 2023
     */
    fun getName() = this.name

    /**
     * Coordinates getter method
     *
     * @return movie coordinates [Coordinates]
     * @author Markov Maxim 2023
     */
    fun getCoordinates() = this.coordinates

    /**
     * Oscars count getter method
     *
     * @return movie oscars count [Int]
     * @author Markov Maxim 2023
     */
    fun getOscarsCount() = this.oscarsCount

    /**
     * Length getter method
     *
     * @return movie length [Long]
     * @author Markov Maxim 2023
     */
    fun getLength() = this.length

    /**
     * Genre getter method
     *
     * @return movie genre [MovieGenre]
     * @author Markov Maxim 2023
     */
    fun getGenre() = this.genre

    /**
     * Mpaa rating getter method
     *
     * @return movie mpaa rating [mpaaRating]
     * @author Markov Maxim 2023
     */
    fun getMpaaRating() = this.mpaaRating
    /**
     * Sreenwriter's name getter method
     *
     * @return movie id [Long]
     * @author Berman Denis 2023
     */
    fun getScreenwriter() = this.screenWriter

    /**
     * Id getter method
     *
     * @return movie id [Long]
     * @author Markov Maxim 2023
     */
    fun getId() = this.id

    /**
     * Creation date getter method
     *
     * @return movie creation date [LocalDate]
     * @author Markov Maxim 2023
     */
    fun getCreationDate() = this.creationDate

    override fun toString(): String {
        return "ID: $id\nName: $name\nCoordinates: $coordinates\nCreation date: $creationDate\n" +
                "Oscars count: $oscarsCount\nLenght: $length\n Genre: $genre\n Mpaa rating: $mpaaRating\n" +
                "Screen writer: $screenWriter"
    }
}