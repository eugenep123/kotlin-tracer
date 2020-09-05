package raytracer.cucumber

import io.cucumber.java8.En
import org.junit.Assert.assertEquals
import raytracer.math.*
import raytracer.shapes.Intersection
import raytracer.shapes.Shape
import raytracer.shapes.Sphere



interface TestHelpers {
    fun Shape.setTransform(t: Transform): Shape = when(this) {
        is Sphere -> copy(transform = t)
        else -> this.also { println("setTransform not supported for $this")}
    }
    fun Shape.normalAt(p: Point): Vector =
        normalAt(p, Intersection(0.0, this))

    fun assertEquals(d1: Double, d2: Double): Unit = assertEquals(d1, d2, EPSILON)

    fun is4x4(m: Matrix): Matrix {
        assertEquals(m.columns, 4)
        assertEquals(m.rows, 4)
        return m
    }

    fun intersects(s: Shape, r: Ray): List<Intersection> = s.intersect(r)
}