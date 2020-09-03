package raytracer.films

import raytracer.math.Color
import raytracer.math.Colors
import raytracer.math.toRgb

class Canvas(val width: Int, val height: Int, fill: Color = Colors.BLACK) {
    private val array: Array<Array<Color>> = Array(height) { Array(width) { fill } }

    operator fun get(x: Int, y: Int): Color = array[y][x]
    operator fun set(x: Int, y: Int, c: Color): Unit {
        array[y][x] = c
    }

    fun forEach(action: (Color) -> Unit): Unit {
        forEachIndexed { _, _, c -> action(c) }
    }

    fun forEachIndexed(action: (Int, Int, Color) -> Unit): Unit {
        array.forEachIndexed { y, row ->
            row.forEachIndexed { x, c -> action(x, y, c) }
        }
    }

    fun toPpmList(): List<String> {
        val headers = listOf("P3", "$width $height", "255")
        val lines = array.toList().flatMap {
            val line = it.joinToString(" ") { it.toRgb() }
            if (line.length < 70) listOf(line)
            else line.chunked
        }
        return headers + lines
    }

    fun toPpm(): String = toPpmList().joinToString(separator = "\n", postfix = "\n")
}



private val String.chunked: List<String>
    get() {
        return this
            .split(" ")
            .toList()
            .fold(emptyList()) { list, part ->
                when {
                    list.isEmpty() -> listOf(part)
                    list.last().length >= 67 -> list + part
                    else ->
                        list.dropLast(1) + (list.last() + " " + part)
                }
            }
    }

