package raytracer.math


data class Point(val x: Double, val y: Double, val z: Double) {
    inline operator fun plus(rhs: Vector): Point = Point(x + rhs.x, y + rhs.y, z + rhs.z)
    inline operator fun minus(rhs: Vector): Point = Point(x - rhs.x, y - rhs.y, z - rhs.z)
    inline operator fun minus(rhs: Point): Vector = Vector(x - rhs.x, y - rhs.y, z - rhs.z)
    inline operator fun unaryMinus(): Point = Point(-x, -y, -z)
    inline operator fun times(d: Double): Point  = Point(x * d, y * d, z * d)
    inline operator fun div(scalar: Double): Point = Point(x / scalar, y / scalar, z / scalar)
}

inline operator fun Vector.plus(rhs: Point): Point = rhs + this
