package raytracer.cucumber.tests

import org.junit.Test
import raytracer.cucumber.glue.shared.toFloat
import raytracer.cucumber.glue.shared.toInt
import raytracer.math.*
import kotlin.math.sqrt
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ManualTest {

    @Test
    fun whenAdding1and3_thenAnswerIs4() {
        val p = Point(0.0f, 1.0f, 0.0f)
        val half_quarter = Transform.rotationX(PI / 4.0f)
        val inv = half_quarter.inverse
        val result = inv(p)
        val expected = Point(0.0f, sqrt(2.0f)/2.0f, -sqrt(2.0f)/2.0f)
        println(p)
        println(result)
        assertEquals(result, expected)

        //Rotate back should be the same
        val result2 = half_quarter(result)
        assertEquals(p, result2)

        val p2 = Point(0.0f, sqrt(2.0f)/2.0f, -sqrt(2.0f)/2.0f)
        assertEquals(p2, expected)
        assertTrue(p2.z eq -0.7071067811865475f)

        assertTrue(-0.7071067811865475f eq toFloat("-√2/2"))
    }


}