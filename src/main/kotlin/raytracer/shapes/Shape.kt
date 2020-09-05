package raytracer.shapes

import raytracer.math.Point
import raytracer.math.Ray
import raytracer.math.Transform
import raytracer.math.Vector
import java.util.UUID


abstract class Shape { //, var material: Material=Material()
    abstract val transform: Transform

    val objectToWorld: Transform
        get() = transform

    val worldToObject: Transform
        get() = transform.inverse

    val uuid: String = UUID.randomUUID().toString()

    fun intersect(ray: Ray): List<Intersection> {
        val localRay = worldToObject(ray)
        return localIntersect(localRay)
    }

    fun normalAt(point: Point, hit: Intersection): Vector {
        val localPoint = worldToObject(point)
        val localNormal = localNormalAt(localPoint, hit)
        return normalToWorld(localNormal)
    }

    fun normalToWorld(normal: Vector): Vector {
        val n = (transform.inverse.matrix.transpose * normal).normalize
//        parent.fold(n)(_.normalToWorld(n))
        return n
    }

    abstract fun localIntersect(ray: Ray): List<Intersection>
    abstract fun  localNormalAt(localPoint: Point, hit: Intersection): Vector
}




