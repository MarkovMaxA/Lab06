package common.entities

import common.SetOverflowException
import java.time.LocalDate
import java.util.*
import kotlin.collections.HashSet


/**
 * Movie manager representative class
 */
class MovieManager {
    private val movieQueue: HashSet<Movie> = HashSet()
    private val creationDate: LocalDate = LocalDate.now()
    private val maxElements = 3

    /**
     * Get movie queue method
     *
     * @return queue of movies [PriorityQueue]
     * @author Markov Maxim 2023
     */
    fun getMovieQueue() = Collections.unmodifiableSet(movieQueue)

    /**
     * add movie to collection method
     *
     * @argument new element of collection [Movie]
     * @return true if element was added.
     * @author Markov Maxim 2023
     */
    fun addMovie(movie: Movie): Boolean {
        if (movieQueue.size >= maxElements) throw SetOverflowException("Collection has maximum elements")
        movieQueue.add(movie)
        return true
    }

    /**
     * remove element by id from collection
     *
     * @argument id element id from collection
     * @return true if element was deleted.
     * @author Markov Maxim 2023
     */
    fun removeElementById(id: Long): Boolean {
        var elementToDelete : Movie? = null
        for (element in movieQueue) {
            if (element.getId() == id) {
                elementToDelete = element
            }
        }

        if (elementToDelete != null) {
            movieQueue.remove(elementToDelete)
            return true
        }

        return false
    }

    /**
     * get collection creation date
     *
     * @return collection creation date [LocalDate]
     * @author Markov Maxim 2023
     */
    fun getCreationDate() = creationDate

    /**
     * get collection class
     *
     * @return class of collection
     * @author Markov Maxim 2023
     */
    fun getCollectionClass() = movieQueue.javaClass

    /**
     * get number of collection elements
     *
     * @return number of elements in collection
     * @author Markov Maxim 2023
     */
    fun getCollectionNumberOfElements() = movieQueue.size
}