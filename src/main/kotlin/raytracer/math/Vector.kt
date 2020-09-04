package raytracer.math

import java.util.zip.DeflaterOutputStream
import kotlin.math.sqrt

data class Vector(val x: Double, val y: Double, val z: Double) {

    val normalize: Vector
        get() = this / magnitude

    val magnitude: Double get() = sqrt(x * x + y * y + z * z)

    inline operator fun plus(rhs: Vector) = Vector(x + rhs.x, y + rhs.y, z + rhs.z)
    inline operator fun minus(rhs: Vector): Vector = Vector(x - rhs.x, y - rhs.y, z - rhs.z)
    inline operator fun unaryMinus(): Vector = Vector(-x, -y, -z)
    operator fun times(scalar: Double): Vector  = Vector(x * scalar, y * scalar, z * scalar)
    operator fun div(scalar: Double): Vector = Vector(x / scalar, y / scalar, z / scalar)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Vector
        return (x eq other.x) &&  (y eq other.y) && (z eq other.z)
    }

    inline fun cross(other: Vector): Vector = cross(this, other)

    inline fun dot(other: Vector): Double = dot(this, other)
}

inline fun dot(a: Vector, b: Vector): Double {
    return a.x * b.x + a.y * b.y + a.z * b.z
}
inline fun cross(a: Vector, b: Vector): Vector {
    return Vector(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x)
}

inline fun reflect(i: Vector, n: Vector): Vector =
    i - 2.0 * dot(n, i) * n

inline operator fun Double.times(v: Vector) =
    Vector(this * v.x, this * v.y, this * v.z)


