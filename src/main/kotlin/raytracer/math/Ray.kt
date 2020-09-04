package raytracer.math

data class Ray(val origin: Point, val direction: Vector) {
    inline fun position(t: Double): Point = origin + direction * t
    inline operator fun invoke(m: Matrix): Ray = Ray(m * origin, m * direction)
}