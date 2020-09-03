package raytracer.math

import kotlin.math.abs

const val PI            = 3.1415926536f
const val EPSILON       = 0.00001f
const val EPSILON_TEST  = 0.0001f

inline infix fun Float.eq(rhs: Float): Boolean =
    abs(this - rhs) <= EPSILON

inline infix fun Float.neq(rhs: Float): Boolean =
    abs(this - rhs) > EPSILON

