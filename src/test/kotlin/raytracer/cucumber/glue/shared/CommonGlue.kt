package raytracer.cucumber.glue.shared

import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import raytracer.films.Canvas
import raytracer.math.*
import kotlin.math.sqrt
import kotlin.test.assertEquals

// TODO: add proper parser
fun toFloat(s: String?): Float =
    when (s?.trim()) {
        "√14" -> sqrt(14.0f)
        "√2/2" -> sqrt(2.0f) / 2.0f
        "-160/532" -> -160f/532f
        "105/532" -> 105f/532f
        else -> java.lang.Float.parseFloat(s!!.trim())
    }

fun toInt(s: String?): Int = java.lang.Integer.parseInt(s!!.trim())

class CommonGlue : En {
    init {

        val FLOAT = "[-+]?\\d+(?:\\.\\d+)?"
        val REAL = "([√]?$FLOAT(?:\\/[√]?$FLOAT)?)"


        // Weird numbers like: √14 and √2/2
        ParameterType("real", "($REAL)") { s: String? ->
            toFloat(s)
        }

        ParameterType("vector", "vector\\($REAL, $REAL, $REAL\\)") { x: String?, y: String?, z: String? ->
            Vector(toFloat(x), toFloat(y), toFloat(z))
        }

        ParameterType("point", "point\\($REAL, $REAL, $REAL\\)") { x: String?, y: String?, z: String? ->
            Point(toFloat(x), toFloat(y), toFloat(z))
        }

        ParameterType(
            "color",
            "(red|green|blue|white|black)|color\\($REAL, $REAL, $REAL\\)"
        ) { name: String?, r: String?, g: String?, b: String? ->
            name?.toColor() ?: Color(toFloat(r), toFloat(g), toFloat(b))
        }

        ParameterType("canvas", "canvas\\(([0-9]+),\\s*([0-9]+)\\)") { width: String?, height: String? ->
            Canvas(toInt(width), toInt(height))
        }

        DataTableType<Matrix> { dt: DataTable? -> dt!!.toMatrix }

        ParameterType("index", "([0-3]+)") { index: String? ->
            Integer.parseInt(index)
        }

        ParameterType("identity", "(identity_matrix)") { _ -> Matrix.Identity }
    }
}

// Convert a dataTable to a Matrix
val DataTable.toMatrix: Matrix
    get()  {
        val size = width()
        assertEquals(width(), height())
        val array = Array<FloatArray>(size) { y ->
            FloatArray(size) { x ->
                val value = cell(y, x)
                toFloat(value)
            }
        }
        return Matrix.of(array)
    }

