package raytracer.cucumber.glue.vectors

import org.junit.Assert.assertEquals
import raytracer.cucumber.glue.BaseSteps
import raytracer.math.*

class VectorsStepDefs : BaseSteps {
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

    init {
        Given("v ← {vector}") { vector: Vector -> v = vector }
        Given("v1 ← {vector}") { vector: Vector -> v1 = vector }
        Given("v2 ← {vector}") { vector: Vector -> v2 = vector }
        Given("zero ← {vector}") { vector: Vector -> zero = vector }

        Then("v.x = {real}") { x: Float -> assertEquals(v.x, x) }
        Then("v.y = {real}") { y: Float -> assertEquals(v.y, y) }
        Then("v.z = {real}") { z: Float -> assertEquals(v.z, z) }

        Given("p ← {point}") { point: Point -> p = point }
        Given("p1 ← {point}") { point: Point -> p1 = point }
        Given("p2 ← {point}") { point: Point -> p2 = point }

        Then("p.x = {real}") { x: Float ->
            assertEquals(p.x, x)
        }
        Then("p.y = {real}") { y: Float -> assertEquals(p.y, y) }
        Then("p.z = {real}") { z: Float -> assertEquals(p.z, z) }

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

        Then("v * {real} = {vector}") { f: Float, vector: Vector -> assertEquals(v * f, vector) }
        Then("p * {real} = {point}") { f: Float, point: Point -> assertEquals(p * f, point) }

        Then("v / {real} = {vector}") { f: Float, vector: Vector -> assertEquals(v / f, vector) }
        Then("p / {real} = {point}") { f: Float, point: Point -> assertEquals(p / f, point) }

//        Then("v.magnitude = {real}") { f: Float -> assertEquals(v.magnitude, f) }
        Then("v.magnitude = {real}") { f: Float -> assertEquals(v.magnitude, f) }
        Then("v.normalize = {vector}") { vector: Vector -> assertEquals(v.normalize, vector) }
        Then("v.normalize = approximately {vector}") { vector: Vector -> assertEquals(v.normalize, vector) }
        When("norm ← v.normalize") { norm = v.normalize }
        Then("norm.magnitude = {real}") { f: Float -> assertEquals(norm.magnitude, f) }

        Then("dot\\(v1, v2) = {real}") { expected: Float -> assertEquals(dot(v1, v2), expected) }
        Then("cross\\(v1, v2) = {vector}") { vector: Vector -> assertEquals(cross(v1, v2), vector) }
        Then("cross\\(v2, v1) = {vector}") { vector: Vector -> assertEquals(cross(v2, v1), vector) }

        Given("c ← {color}") { color: Color -> c = color }
        Given("c1 ← {color}") { color: Color -> c1 = color }
        Given("c2 ← {color}") { color: Color -> c2 = color }

        Then("c.red = {real}") { f: Float -> assertEquals(c.red, f) }
        Then("c.green = {real}") { f: Float -> assertEquals(c.green, f) }
        Then("c.blue = {real}") { f: Float -> assertEquals(c.blue, f) }

        Then("c1 + c2 = {color}") { color: Color -> assertEquals(c1 + c2, color) }
        Then("c1 - c2 = {color}") { color: Color -> assertEquals(c1 - c2, color) }
        Then("c * {real} = {color}") { f: Float, color: Color -> assertEquals(c * f, color) }
        Then("c1 * c2 = {color}") { color: Color -> assertEquals(c1 * c2, color) }

        Given("n ← {vector}") { vector: Vector -> n = vector }
        When("r ← reflect\\(v, n)") { r = reflect(v, n) }
        Then("r = {vector}") { vector: Vector -> assertEquals(r, vector) }

    }

}
