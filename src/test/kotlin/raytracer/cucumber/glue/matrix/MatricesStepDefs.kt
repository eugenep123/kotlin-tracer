package raytracer.cucumber.glue.matrix

import io.cucumber.java8.PendingException
import raytracer.cucumber.glue.BaseSteps
import raytracer.math.Matrix
import raytracer.math.Point
import raytracer.math.Vector
import raytracer.math.times
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class MatricesStepDefs : BaseSteps {
    lateinit var matrix: Matrix
    lateinit var A: Matrix
    lateinit var B: Matrix
    lateinit var p: Point
    lateinit var v: Vector


    init {

        Given("the following {int}x{int} matrix M:") { rows: Int, cols: Int, m: Matrix ->
            assertEquals(rows, cols)
            assertEquals(m.rows, rows)
            matrix = m
        }
        Given("the following matrix A:") { m: Matrix ->
            assertEquals(m.rows, 4)
            assertEquals(m.columns, 4)
            A = m
        }
        Given("the following matrix B:") { m: Matrix ->
            assertEquals(m.rows, 4)
            assertEquals(m.columns, 4)
            B = m
        }

        // Issue: "M[0,0]" is picked up as "M[{double}], so convert it to "M[0, 0]" (with space)
        Then("M[{index}, {index}] = {real}") { row: Int, col: Int, expected: Float ->
            assertEquals(matrix[row, col], expected)
        }

        Then("A = B") { assertEquals(A, B) }

        Then("A != B") { assertNotEquals(A, B) }

        Then("A * B is the following 4x4 matrix:") { expected: Matrix ->
            assertEquals(A * B, expected)
        }

        Given("p ← {point}") { point: Point -> p = point }
        Given("v ← {vector}") { vector: Vector -> v = vector }

        Then("A * p = {point}") { point: Point ->
            assertEquals(A * p, point)
        }
//        Then("A * v = {vector}") { vector: Vector ->
//            assertEquals(A * v, vector)
//        }

        Then("A * {identity} = A") { id: Matrix -> assertEquals(A * id, A) }
        Then("{identity} * p = p") { id: Matrix -> assertEquals(id * p, p) }
        Then("{identity} * v = v") { id: Matrix -> assertEquals(id * v, v) }
    }
}



