package raytracer.cucumber.glue.shapes

import io.cucumber.java8.PendingException
import raytracer.cucumber.glue.BaseSteps
import raytracer.math.*
import raytracer.shapes.Intersection
import raytracer.shapes.Shape
import raytracer.shapes.Sphere
import kotlin.test.assertEquals
import kotlin.test.assertSame


//fun normalAt(s: Shape, p: Point) = s.normalAt(p, Intersection(0.0, s))

class SpheresStepDef : BaseSteps {
    lateinit var r: Ray
    lateinit var s: Sphere
    lateinit var xs: List<Intersection>
    lateinit var t: Transform
    lateinit var n: Vector

    fun setTransform(t: Transform) {
        s = s.copy(transform = t)
        println(s)
    }


    init {
        Given("r ← {ray}") { ray: Ray -> r = ray }

        Given("s ← sphere\\()") { throw PendingException() }
        When("xs ← intersect\\(s, r)") { xs = intersects(s, r) }
        Then("xs.count = {int}") { count: Int -> assertEquals(xs.size, count) }
        Then("xs[{index}] = {real}") { index: Int, expected: Double -> assertEquals(xs[index].time, expected) }
        Given("s ← {shape}") { shape: Shape -> s = shape as Sphere }
        Then("s.transform = {identity}") { identity: Matrix4 ->
            assertEquals(Transform.Identity.matrix, identity)
            assertEquals(s.transform, Transform.Identity)
        }
        Given("t ← {translation}") { translation: Transform -> t = translation }
        Then("s.transform = t") { assertEquals(s.transform, t) }

        When("set_transform\\(s, t)") { setTransform(t) }
        When("set_transform\\(s, {scaling})") { t: Transform -> setTransform(t) }
        When("set_transform\\(s, {translation})") { t: Transform -> setTransform(t) }

        Then("xs[{index}].t = {real}") { index: Int, expected: Double -> assertEquals(xs[index].time, expected) }
        Then("xs[{index}].object = s") { index: Int -> assertSame(xs[index].shape, s) }

        When("n ← normal_at\\(s, {point})") { point: Point -> n = s.normalAt(point) }
        Then("n = {vector}") { vector: Vector -> assertEquals(n, vector) }

        Given("t ← {scaling} * {rotation}") { scaling: Transform, rotation: Transform -> t = scaling * rotation }

        Then("n = normalize\\(n)") { assertEquals(n, n.normalize) }

        When("m ← s.material") { throw PendingException() }
    }
}