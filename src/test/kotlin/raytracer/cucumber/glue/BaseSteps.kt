package raytracer.cucumber.glue

import io.cucumber.java8.En
import org.junit.Assert.assertEquals
import raytracer.math.EPSILON

interface BaseSteps : En {
    fun assertEquals(d1: Float, d2: Float): Unit = assertEquals(d1, d2, EPSILON)



}