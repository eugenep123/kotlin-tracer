package raytracer.cucumber.glue.matrix

import raytracer.cucumber.glue.BaseSteps
import raytracer.math.Matrix
import kotlin.test.assertEquals

class MatricesStepDefs : BaseSteps {
    lateinit var matrix: Matrix

    init {

        Given("the following 4x4 matrix M:") { m: Matrix ->
            assertEquals(m.array.size, 4)
            matrix = m
        }

        Then("M\\[{int}, {int}] = {float}") { row: Int, col: Int, expected: Float ->
            assertEquals(matrix[row, col], expected)
        }
    }
}



