package raytracer.cucumber.glue.transforms

import raytracer.cucumber.glue.BaseSteps
import raytracer.math.Matrix
import raytracer.math.Point
import raytracer.math.Transform
import raytracer.math.Vector
import kotlin.test.assertEquals


class TransformationsStepDef : BaseSteps {
    lateinit var transform: Transform
    lateinit var inv: Transform
    lateinit var halfQuarter: Transform
    lateinit var fullQuarter: Transform
    lateinit var A: Transform
    lateinit var B: Transform
    lateinit var C: Transform

    lateinit var p: Point
    lateinit var v: Vector
    lateinit var p2: Point
    lateinit var p3: Point
    lateinit var p4: Point
    lateinit var from: Point
    lateinit var to: Point
    lateinit var up: Vector
    lateinit var t: Transform

    init {
        Given("transform ← {translation}") { t: Transform -> transform = t }
        Given("transform ← {scaling}") { scaling: Transform -> transform = scaling }
        Given("full_quarter ← {rotation}") { transform: Transform -> fullQuarter = transform }
        Given("half_quarter ← {rotation}") { transform: Transform -> halfQuarter = transform }
        Given("transform ← {shearing}") { shearing: Transform -> transform = shearing }
        Given("A ← {rotation}") { t: Transform -> A = t }
        Given("B ← {scaling}") { t: Transform -> B = t }
        Given("C ← {translation}") { t: Transform -> C = t }

        Given("p ← {point}") { point: Point -> p = point }
        Given("v ← {vector}") { vector: Vector -> v = vector }

        Given("inv ← inverse\\(transform)") { inv = transform.inverse }
        Given("inv ← inverse\\(half_quarter)") { inv = halfQuarter.inverse }

        Then("inv * p = {point}") { point: Point -> assertEquals(inv(p), point) }
        Then("transform * p = {point}") { point: Point -> assertEquals(transform(p), point) }

        Then("transform * v = v") { assertEquals(transform(v), v) }
        Then("transform * v = {vector}") { vector: Vector -> assertEquals(transform(v), vector)  }
        Then("inv * v = {vector}") { vector: Vector -> assertEquals(inv(v), vector) }

        Then("full_quarter * p = {point}") { point: Point? -> assertEquals(fullQuarter(p), point) }
        Then("half_quarter * p = {point}") { point: Point? -> assertEquals(halfQuarter(p), point) }


        When("p2 ← A * p") { p2 = A(p) }
        When("p3 ← B * p2") { p3 = B(p2) }
        When("p4 ← C * p3") { p4 = C(p3) }
        Then("p2 = {point}") { point: Point -> assertEquals(p2, point) }
        Then("p3 = {point}") { point: Point -> assertEquals(p3, point) }
        Then("p4 = {point}") { point: Point -> assertEquals(p4, point) }

        When("T ← C * B * A") { t = C * B * A }
        Then("T * p = {point}") { point: Point -> assertEquals(t(p), point) }

        Given("from ← {point}") { point: Point -> from = point }
        Given("to ← {point}") { point: Point -> to = point }
        Given("up ← {vector}") { vector: Vector -> up = vector }
        When("t ← view_transform\\(from, to, up)") { t = Transform.view(from, to, up)!! }
        Then("t = {identity}") { identity: Matrix -> assertEquals(t.matrix, identity) }

        Then("t = {scaling}") { scaling: Transform -> t = scaling }
        Then("t = {translation}") { translation: Transform -> t = translation }

        Then("t is the following 4x4 matrix:") { expected: Matrix ->
            assertEquals(t.matrix, expected)
        }
    }
}