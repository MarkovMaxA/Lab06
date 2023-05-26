package common.entities

import common.EmptyStringException
import common.ValueLessThanZeroException
import kotlinx.serialization.Serializable

/**
 * Colors enum representative class
 */
enum class Color {
    GREEN,
    BLACK,
    WHITE,
    BROWN
}

/**
 * Countries enum representative class
 */
enum class Country {
    RUSSIA,
    SPAIN,
    CHINA,
    VATICAN,
    INDIA
}

/**
 * Person representation class
 */
@Serializable
class Person(private val name: String, private val height: Int,
             private val hairColor: Color, private val nationality: Country?) {
    init {
        checkNameRestrictions(name)
        checkHeightRestrictions(height)
    }

    companion object {
        @JvmStatic
        fun checkNameRestrictions(name: String) {
            if (name.isEmpty()) throw EmptyStringException("Person need's name")
            if (name.contains(",")) throw EmptyStringException("Name can't use symbol ','")
        }

        @JvmStatic
        fun checkHeightRestrictions(height: Int) {
            if (height <= 0) throw ValueLessThanZeroException("Height can't be less than zero")
        }
    }

    /**
     * Name getter method
     *
     * @return person name [String]
     * @author Markov Maxim 2023
     */
    fun getName() = this.name

    /**
     * Height getter method
     *
     * @return person height [Float]
     * @author Markov Maxim 2023
     */
    fun getHeight() = this.height

    /**
     * Hair color getter method
     *
     * @return person hair color [Color]
     * @author Markov Maxim 2023
     */
    fun getHairColor() = this.hairColor

    /**
     * Nationality getter method
     *
     * @return person nationality [Country]
     * @author Markov Maxim 2023
     */
    fun getNationality() = this.nationality

    override fun toString(): String = "Name: $name Height: $height Hair Color: $hairColor Nationality: $nationality"
}