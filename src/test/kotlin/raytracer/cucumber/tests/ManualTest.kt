package raytracer.cucumber.tests

import org.junit.Assert.assertEquals
import org.junit.Test
import raytracer.cucumber.glue.shared.toNumber
import raytracer.math.*
import kotlin.math.sqrt

class ManualTest {


    @Test
    fun toNumber_should_parse_correctly() {
        val expected = -1.41421356237
        assertEquals(-sqrt(2.0), toNumber("-√2"), EPSILON)
        assertEquals(-sqrt(2.0), expected, EPSILON)
        assertEquals(toNumber("-√2"), expected, EPSILON)
    }
    @Test
    fun whenAdding1and3_thenAnswerIs4() {
        val p = Point(0.0, 1.0, 0.0)
        val half_quarter = Transform.rotationX(PI / 4.0)
        val inv = half_quarter.inverse
        val result = inv(p)
        val expected = Point(0.0, sqrt(2.0)/2.0, -sqrt(2.0)/2.0)
        assertEquals(result, expected)

        //Rotate back should be the same
        val result2 = half_quarter(result)
        assertEquals(p, result2)

        val p2 = Point(0.0, sqrt(2.0)/2.0, -sqrt(2.0)/2.0)
        assertEquals(p2, expected)
        assertEquals(p2.z, -0.7071067811865475, EPSILON)


        assertEquals(-sqrt(2.0)/2.0, toNumber("-√2/2"), EPSILON)
        assertEquals(-0.7071067811865475, -sqrt(2.0)/2.0, EPSILON)
        assertEquals(-0.7071067811865475, toNumber("-√2/2"), EPSILON)
    }

    @Test
    fun test_ray_scaling() {
        val r = Ray(Point(1.0, 2.0, 3.0), Vector(0.0, 1.0, 0.0))
        val m = Transform.scale(2.0, 3.0, 4.0)
        val r2 = m(r) //transform(r, m)
        assertEquals(r2.origin, Point(2.0, 6.0, 12.0))
        assertEquals(r2.direction, Vector(0.0, 3.0, 0.0))
    }
}