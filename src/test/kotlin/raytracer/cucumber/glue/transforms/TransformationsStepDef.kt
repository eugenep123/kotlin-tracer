package raytracer.cucumber.glue.transforms


import io.cucumber.java8.PendingException
import raytracer.cucumber.glue.BaseSteps
import raytracer.math.Point
import raytracer.math.Transform
import raytracer.math.Vector
import kotlin.test.assertEquals

class TransformationsStepDef : BaseSteps {
    lateinit var transform: Transform
    lateinit var inv: Transform
    lateinit var p: Point
    lateinit var v: Vector
    lateinit var halfQuarter: Transform
    lateinit var fullQuarter: Transform

    init {
        Given("transform ← {translation}") { t: Transform -> transform = t }
        Given("transform ← {scaling}") { scaling: Transform -> transform = scaling }

        Given("p ← {point}") { point: Point -> p = point }
        Given("v ← {vector}") { vector: Vector -> v = vector }

        Given("inv ← inverse\\(transform)") { inv = transform.inverse }
        Then("inv * p = {point}") { point: Point -> assertEquals(inv(p), point)  }
        Then("transform * p = {point}") { point: Point -> assertEquals(transform(p), point) }

        Then("transform * v = v") { assertEquals(transform(v), v) }
        Then("transform * v = {vector}") { vector: Vector -> assertEquals(transform(v), vector)  }
        Then("inv * v = {vector}") { vector: Vector -> assertEquals(inv(v), vector) }

        Given("half_quarter ← {rotation_x}") { transform: Transform -> halfQuarter = transform }
        Given("full_quarter ← {rotation_x}") { transform: Transform -> fullQuarter = transform }
        Then("half_quarter * p = {point}") { point: Point? -> assertEquals(halfQuarter(p), point) }
        Then("full_quarter * p = {point}") { point: Point? -> assertEquals(fullQuarter(p), point) }

        Given("inv ← inverse\\(half_quarter)") { inv = halfQuarter.inverse }

    }
}