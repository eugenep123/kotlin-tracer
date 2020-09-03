package raytracer.math

import kotlin.math.abs

const val PI            = 3.1415926536f
const val EPSILON       = 0.00001
const val EPSILON_TEST  = 0.0001

inline infix fun Double.eq(rhs: Double): Boolean =
    abs(this - rhs) <= EPSILON

inline infix fun Double.neq(rhs: Double): Boolean =
    abs(this - rhs) > EPSILON
