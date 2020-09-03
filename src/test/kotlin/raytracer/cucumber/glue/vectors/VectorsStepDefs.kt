package raytracer.cucumber.glue.vectors

import org.junit.Assert.assertEquals
import raytracer.math.*
import io.cucumber.java8.En
import raytracer.math.Color
import raytracer.math.minus
import raytracer.math.plus
import raytracer.math.times

class VectorsStepDefs : En {
    private lateinit var v: Vector
    private lateinit var v1: Vector
    private lateinit var v2: Vector
    private lateinit var zero: Vector
    private lateinit var norm: Vector

    private lateinit var p: Point
    private lateinit var p1: Point
    private lateinit var p2: Point

    private lateinit var c: Color
    private lateinit var c1: Color
    private lateinit var c2: Color
    private lateinit var n: Vector
    private lateinit var r: Vector

    fun assertEquals(d1: Double, d2: Double) = assertEquals(d1, d2, EPSILON)

    init {
        Given("v ← {vector}") { vector: Vector -> v = vector }
        Given("v1 ← {vector}") { vector: Vector -> v1 = vector }
        Given("v2 ← {vector}") { vector: Vector -> v2 = vector }
        Given("zero ← {vector}") { vector: Vector -> zero = vector }

        Then("v.x = {double}") { x: Double -> assert(v.x == x) }
        Then("v.y = {double}") { y: Double -> assert(v.y == y) }
        Then("v.z = {double}") { z: Double -> assert(v.z == z) }

        Given("p ← {point}") { point: Point -> p = point }
        Given("p1 ← {point}") { point: Point -> p1 = point }
        Given("p2 ← {point}") { point: Point -> p2 = point }

        Then("p.x = {double}") { x: Double -> assert(p.x == x) }
        Then("p.y = {double}") { y: Double -> assert(p.y == y) }
        Then("p.z = {double}") { z: Double -> assert(p.z == z) }

        Then("v1 + v2 = {vector}") { vector: Vector -> assertEquals(v1 + v2, vector) }
        Then("v + p = {point}") { point: Point ->
            assertEquals(v + p, point)
            assertEquals(p + v, point)
        }

        Then("p - v = {point}") { point: Point -> assertEquals(p - v, point) }
        Then("p1 - p2 = {vector}") { vector: Vector -> assertEquals(p1 - p2, vector) }
        Then("v1 - v2 = {vector}") { vector: Vector -> assertEquals(v1 - v2, vector) }
        Then("zero - v = {vector}") { vector: Vector -> assertEquals(zero - v, vector) }

        Then("-v = {vector}") { vector: Vector -> assertEquals(-v, vector) }
        Then("-p = {point}") { point: Point -> assertEquals(-p, point) }

        Then("v * {double} = {vector}") { d: Double, vector: Vector -> assertEquals(v * d, vector) }
        Then("p * {double} = {point}") { d: Double, point: Point -> assertEquals(p * d, point) }

        Then("v / {double} = {vector}") { d: Double, vector: Vector -> assertEquals(v / d, vector) }
        Then("p / {double} = {point}") { d: Double, point: Point -> assertEquals(p / d, point) }

        Then("v.magnitude = {double}") { d: Double -> assertEquals(v.magnitude, d) }
        Then("v.magnitude = {sqrt}") { d: Double -> assertEquals(v.magnitude, d) }
        Then("v.normalize = {vector}") { vector: Vector -> assertEquals(v.normalize, vector) }
        Then("v.normalize = approximately {vector}") { vector: Vector -> assertEquals(v.normalize, vector) }
        When("norm ← v.normalize") { norm = v.normalize }
        Then("norm.magnitude = {double}") { d: Double -> assertEquals(norm.magnitude, d) }

        Then("dot\\(v1, v2) = {double}") { expected: Double -> assertEquals(dot(v1, v2), expected) }
        Then("cross\\(v1, v2) = {vector}") { vector: Vector -> assertEquals(cross(v1, v2), vector) }
        Then("cross\\(v2, v1) = {vector}") { vector: Vector -> assertEquals(cross(v2, v1), vector) }

        Given("c ← {color}") { color: Color -> c = color }
        Given("c1 ← {color}") { color: Color -> c1 = color }
        Given("c2 ← {color}") { color: Color -> c2 = color }

        Then("c.red = {double}") { d: Double -> assertEquals(c.red, d) }
        Then("c.green = {double}") { d: Double -> assertEquals(c.green, d) }
        Then("c.blue = {double}") { d: Double -> assertEquals(c.blue, d) }

        Then("c1 + c2 = {color}") { color: Color -> assertEquals(c1 + c2, color) }
        Then("c1 - c2 = {color}") { color: Color -> assertEquals(c1 - c2, color) }
        Then("c * {double} = {color}") { d: Double, color: Color -> assertEquals(c * d, color) }
        Then("c1 * c2 = {color}") { color: Color -> assertEquals(c1 * c2, color) }

        Given("n ← {vector}") { vector: Vector -> n = vector }
        When("r ← reflect\\(v, n)") { r = reflect(v, n) }
        Then("r = {vector}") { vector: Vector -> assertEquals(r, vector) }

    }

}
