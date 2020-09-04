package raytracer.cucumber.glue.matrix

import raytracer.cucumber.glue.BaseSteps
import raytracer.math.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class MatricesStepDefs : BaseSteps {
    lateinit var matrix: Matrix
    lateinit var A: Matrix
    var B: Matrix? = null
    lateinit var C: Matrix
    lateinit var p: Point
    lateinit var v: Vector


    init {

        //the following {int}x{int} matrix M:"
        //the following 4x4 matrix A:
        Given("the following 4x4 matrix M:") { m: Matrix -> matrix = is4x4(m) }
        Given("the following matrix A:") { m: Matrix -> A = is4x4(m) }
        Given("the following matrix B:") { m: Matrix -> B = is4x4(m) }
        Given("the following 4x4 matrix A:") { m: Matrix -> A = is4x4(m) }
        Given("the following 4x4 matrix B:") { m: Matrix -> B = is4x4(m) }

        // Issue: "M[0,0]" is picked up as "M[{double}], so convert it to "M[0, 0]" (with space)
        Then("M[{index}, {index}] = {real}") { row: Int, col: Int, expected: Float ->
            assertEquals(matrix[row, col], expected)
        }
        Then("B[{index}, {index}] = {real}") { row: Int, col: Int, expected: Float ->
            assertEquals(B!![row, col], expected)
        }
        Then("A = B") { assertEquals(A, B) }
        Then("A != B") { assertNotEquals(A, B) }
        Then("A * B is the following 4x4 matrix:") { expected: Matrix ->
            assertEquals(A * B!!, expected)
        }

        Given("p ← {point}") { point: Point -> p = point }
        Given("v ← {vector}") { vector: Vector -> v = vector }
        Then("A * p = {point}") { point: Point -> assertEquals(A * p, point) }
        Then("A * {identity} = A") { id: Matrix -> assertEquals(A * id, A) }
        Then("{identity} * p = p") { id: Matrix -> assertEquals(id * p, p) }
        Then("{identity} * v = v") { id: Matrix -> assertEquals(id * v, v) }

        Then("transpose\\(A) is the following matrix:") { expected: Matrix ->
            assertEquals(A.transpose, expected)
        }
        Then("A ← transpose\\({identity})") { id: Matrix -> A = id.transpose }
        Then("A = {identity}") { id: Matrix -> assertEquals(A, id) }

        Then("determinant\\(A) = {real}") { expected: Float -> assertEquals(A.determinant, expected) }
        Then("A is invertible") { assertTrue(A.isInvertible) }
        Then("A is not invertible") { assertFalse(A.isInvertible) }

        And("B ← inverse\\(A)") { B = A.inverse }
        Then("B is the following 4x4 matrix:") { expected: Matrix -> assertEquals(B, expected) }

        Then("inverse\\(A) is the following 4x4 matrix:") { expected: Matrix -> assertEquals(A.inverse, expected) }

        Given("C ← A * B") { C = A * B!! }
        Then("C * inverse\\(B) = A") { assertEquals(C * B?.inverse!!, A) }


    }
}



