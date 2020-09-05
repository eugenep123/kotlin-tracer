package raytracer.cucumber.glue.rays

import raytracer.cucumber.glue.BaseSteps
import raytracer.math.Point
import raytracer.math.Ray
import raytracer.math.Transform
import raytracer.math.Vector
import kotlin.test.assertEquals


class RaysStepDefs : BaseSteps {
    lateinit var origin: Point
    lateinit var direction: Vector
    lateinit var r: Ray
    lateinit var r2: Ray
    lateinit var m: Transform

    init {

        Given("origin ← {point}") { point: Point-> origin = point }
        Given("direction ← {vector}") { vector: Vector -> direction = vector }
        When("r ← ray\\(origin, direction)") { r = Ray(origin, direction) }
        Then("r.origin = origin") { assertEquals(r.origin, origin) }
        Then("r.direction = direction") { assertEquals(r.direction, direction) }

        Given("r ← {ray}") { ray: Ray -> r = ray }
        Then("position\\(r,{real}) = {point}") { time: Double, point: Point ->
            assertEquals(r.position(time), point) }

        Given("m ← {translation}") { translation: Transform -> m = translation }

        When("r2 ← transform\\(r, m)") { r2 = m(r) }
        Then("r2.origin = {point}") { point: Point? ->  assertEquals(r2.origin, point) }
        Then("r2.direction = {vector}") { vector: Vector? -> assertEquals(r2.direction, vector) }

        Given("m ← {scaling}") { scaling: Transform -> m = scaling }
    }
}