package raytracer.shapes

import raytracer.math.Point
import raytracer.math.Ray
import raytracer.math.Transform
import raytracer.math.Vector
import kotlin.math.pow
import kotlin.math.sqrt

data class Sphere(override val transform: Transform = Transform.Identity) : Shape() {

    override fun localIntersect(ray: Ray): List<Intersection> {
        // vector from the sphere's center, to the ray origin
        // remember: the sphere is centered at the world origin
        val sphereToRay = ray.origin - Point.origin
        val a = ray.direction.dot(ray.direction)
        val b = 2.0 * ray.direction.dot(sphereToRay)
        val c = sphereToRay.dot(sphereToRay) - 1
        val discriminant = b.pow(2) - 4.0 * a * c

        return when {
            discriminant < 0 -> emptyList()
            else -> {
                val t1 = (-b - sqrt(discriminant)) / (2.0 * a)
                val t2 = (-b + sqrt(discriminant)) / (2.0 * a)
                listOf(
                    Intersection(t1, this),
                    Intersection(t2, this)
                )
            }
        }
    }

    override fun localNormalAt(localPoint: Point, hit: Intersection): Vector {
        return localPoint - Point.origin
    }

}
