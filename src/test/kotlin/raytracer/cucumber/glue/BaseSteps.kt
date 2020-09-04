package raytracer.cucumber.glue

import io.cucumber.java8.En
import org.junit.Assert.assertEquals
import raytracer.math.EPSILON
import raytracer.math.Matrix

interface BaseSteps : En {
    fun assertEquals(d1: Double, d2: Double): Unit = assertEquals(d1, d2, EPSILON)

    fun is4x4(m: Matrix): Matrix {
        assertEquals(m.columns, 4)
        assertEquals(m.rows, 4)
        return m
    }

}