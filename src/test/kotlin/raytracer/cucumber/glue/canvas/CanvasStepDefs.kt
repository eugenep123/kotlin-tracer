package raytracer.cucumber.glue.canvas

import io.cucumber.java8.En
import raytracer.films.Canvas
import raytracer.math.Color
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CanvasStepDefs : En {

    lateinit var can: Canvas
    lateinit var ppm: List<String>
    lateinit var c1: Color
    lateinit var c2: Color
    lateinit var c3: Color

    fun ppmLines(start: Int, end: Int) = ppm.drop(start - 1).take(end - start + 1)


    init {

        Given("c ← {canvas}") { canvas: Canvas -> can = canvas }
        Then("c.width = {int}") { width: Int -> assertEquals(can.width, width) }
        Then("c.height = {int}") { height: Int -> assertEquals(can.height, height) }

        Then("every pixel of c is {color}") { color: Color ->
            can.forEach { c -> assertEquals(c, color) }
        }

        When("write_pixel\\(c, {int}, {int}, {color})") { x: Int, y: Int, color: Color ->
            can[x, y] = color
        }

        Then("pixel_at\\(c, {int}, {int}) = {color}") { x: Int, y: Int, color: Color ->
            assertEquals(can[x, y], color)
        }

        When("ppm ← canvas_to_ppm\\(c)") {
            ppm = can.toPpmList()
        }


        Then("lines {int}-{int} of ppm are") { start: Int, end: Int, expected: String ->

            val trimmed = expected
                .split("\n")
                .map { it.trim() }
                .toList()
            val taken = ppmLines(start, end)
            println(trimmed.joinToString("\n"))
            println("")
            println(taken.joinToString("\n"))

            assertEquals(taken, trimmed)
        }

        Given("c1 ← {color}") { color: Color -> c1 = color }
        Given("c2 ← {color}") { color: Color -> c2 = color }
        Given("c3 ← {color}") { color: Color -> c3 = color }
        When("write_pixel\\(c, {int}, {int}, c1)") { x: Int, y: Int -> can[x, y] = c1 }
        When("write_pixel\\(c, {int}, {int}, c2)") { x: Int, y: Int -> can[x, y] = c2 }
        When("write_pixel\\(c, {int}, {int}, c3)") { x: Int, y: Int -> can[x, y] = c3 }

        When("every pixel of c is set to {color}") { color: Color ->
            can.forEachIndexed { x, y, _ -> can[x, y] = color }
        }

        Then("the last character of ppm is a newline") {
            assertTrue(can.toPpm().endsWith("\n"))
        }
    }

}