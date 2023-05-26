package common.entities

import common.MaxValueException
import kotlinx.serialization.Serializable

@Serializable
class Coordinates(private val x: Float, private val y: Double) {
    init {
        checkYRestrictions(y)
    }
    companion object {
        @JvmStatic
        fun checkYRestrictions(y: Double) {
            if (y > 424) throw MaxValueException("Y can't be more than 424")
        }
    }

    /**
     * X getter method
     *
     * @return x coordinate [Float]
     * @author Markov Maxim 2023
     */
    fun getX() = this.x

    /**
     * Y getter method
     *
     * @return y coordinate [Double]
     * @author Markov Maxim 2023
     */
    fun getY() = this.y

    override fun toString(): String {
        return "X: $x Y: $y"
    }
}