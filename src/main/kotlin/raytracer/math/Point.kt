package raytracer.math

data class Point(val x: Float, val y: Float, val z: Float) {
    inline operator fun plus(rhs: Vector): Point = Point(x + rhs.x, y + rhs.y, z + rhs.z)
    inline operator fun minus(rhs: Vector): Point = Point(x - rhs.x, y - rhs.y, z - rhs.z)
    inline operator fun minus(rhs: Point): Vector = Vector(x - rhs.x, y - rhs.y, z - rhs.z)
    inline operator fun unaryMinus(): Point = Point(-x, -y, -z)
    inline operator fun times(scalar: Float): Point  = Point(x * scalar, y * scalar, z * scalar)
    inline operator fun div(scalar: Float): Point = Point(x / scalar, y / scalar, z / scalar)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Point
        return (x eq other.x) &&  (y eq other.y) && (z eq other.z)
    }
}

inline operator fun Vector.plus(rhs: Point): Point = rhs + this
