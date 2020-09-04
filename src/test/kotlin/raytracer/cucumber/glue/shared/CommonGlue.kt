package raytracer.cucumber.glue.shared

import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import raytracer.films.Canvas
import raytracer.math.*
import java.lang.Double.parseDouble
import kotlin.math.sqrt
import kotlin.test.assertEquals

fun toNumber(sn: String?): Double {
    val s = sn!!
    return when {
        s.startsWith(" ") -> toNumber(s.trim())
        s.contains("/") -> {
            val index = s.indexOf('/')
            val dividend = toNumber(s.substring(0, index))
            val divisor = toNumber(s.drop(index + 1))
            dividend / divisor
        }
        s.startsWith("-√") -> -sqrt(toNumber(s.drop(2)))
        s.startsWith("√") -> sqrt(toNumber(s.drop(1)))
        s == "π" -> PI
        s == "-π" -> -PI
        else -> parseDouble(s.trim())
    }
}


fun toInt(s: String?): Int = Integer.parseInt(s!!.trim())
/*
 * Common issues:
 *  - "π / 4" does not parse, make it: "π/4
 */
class CommonGlue : En {
    init {


        // Capture all possible numeric expression characters and parse it later
        val REAL = "\\s*([0-9\\.\\-\\+√\\/π]+)\\s*"

        // Weird numbers like: √14 and √2/2
        ParameterType("real", "($REAL)") { s: String? ->
            toNumber(s)
        }

        ParameterType("vector", "vector\\($REAL, $REAL, $REAL\\)") { x: String?, y: String?, z: String? ->
            Vector(toNumber(x), toNumber(y), toNumber(z))
        }

        ParameterType("point", "point\\($REAL, $REAL, $REAL\\)") { x: String?, y: String?, z: String? ->
            Point(toNumber(x), toNumber(y), toNumber(z))
        }

        ParameterType(
            "color",
            "(red|green|blue|white|black)|color\\($REAL, $REAL, $REAL\\)"
        ) { name: String?, r: String?, g: String?, b: String? ->
            name?.toColor() ?: Color(toNumber(r), toNumber(g), toNumber(b))
        }

        ParameterType("canvas", "canvas\\(([0-9]+),\\s*([0-9]+)\\)") { width: String?, height: String? ->
            Canvas(toInt(width), toInt(height))
        }

        DataTableType<Matrix> { dt: DataTable? -> dt!!.toMatrix }

        ParameterType("index", "([0-3]+)") { index: String ->
            Integer.parseInt(index)
        }

        ParameterType("identity", "(identity_matrix)") { _ -> Matrix.Identity }

        // Transforms
        ParameterType("translation", "translation\\($REAL, $REAL, $REAL\\)") { x: String?, y: String?, z: String? ->
            Transform.translate(toNumber(x), toNumber(y), toNumber(z))
        }
        ParameterType("scaling", "scaling\\($REAL, $REAL, $REAL\\)") { x: String?, y: String?, z: String? ->
            Transform.scale(toNumber(x), toNumber(y), toNumber(z))
        }

        ParameterType("rotation_x", "rotation_x\\($REAL\\)") { r: String? ->
            Transform.rotationX(toNumber(r))
        }
    }
}

// Convert a dataTable to a Matrix
val DataTable.toMatrix: Matrix
    get()  {
        val size = width()
        assertEquals(width(), height())
        val array = Array<DoubleArray>(size) { y ->
            DoubleArray(size) { x ->
                val value = cell(y, x)
                toNumber(value)
            }
        }
        return Matrix.of(array)
    }

