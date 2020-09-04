package raytracer.cucumber.glue

import io.cucumber.java8.En
import org.junit.Assert.assertEquals
import raytracer.math.EPSILON
import raytracer.math.Matrix

interface BaseSteps : En {
    fun assertEquals(d1: Float, d2: Float): Unit = assertEquals(d1, d2, EPSILON)

    fun is4x4(m: Matrix): Matrix {
        kotlin.test.assertEquals(m.columns, 4)
        kotlin.test.assertEquals(m.rows, 4)
        return m
    }

}