package raytracer.cucumber.glue.matrix

import io.cucumber.java8.En
import io.cucumber.java8.PendingException

class MatricesStepDefs : En {
    init {
        Then("M[{int}, {int}] = {double}") { row: Int, col: Int, expected: Double ->
//            assertEquals(matrix2[row, col], expected)
        }

        /*
        Given("b ← tuple\\({int}, {int}, {int}, {int})", (Integer int1, Integer int2, Integer int3, Integer int4) -> {
         // Write code here that turns the phrase above into concrete actions
            throw new io.cucumber.java8.PendingException();
        });
         */
        Given("b ← tuple\\({int}, {int}, {int}, {int})") { int1: Int, int2: Int?, int3: Int?, int4: Int? ->
            throw PendingException()
        }
    }
}