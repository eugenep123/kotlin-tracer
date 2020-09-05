package raytracer.cucumber.glue.shared

import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import raytracer.films.Canvas
import raytracer.math.*
import raytracer.shapes.Shape
import raytracer.shapes.Sphere
import java.lang.Double.parseDouble
import kotlin.math.sqrt
import kotlin.test.assertEquals
import kotlin.test.fail

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

         //Transforms
        ParameterType("translation", "translation\\($REAL, $REAL, $REAL\\)") { x: String?, y: String?, z: String? ->
            Transform.translate(toNumber(x), toNumber(y), toNumber(z))
        }

        ParameterType("scaling", "scaling\\($REAL, $REAL, $REAL\\)") { x: String?, y: String?, z: String? ->
            Transform.scale(toNumber(x), toNumber(y), toNumber(z))
        }

        ParameterType(
            "rotation",
            "(rotation_x|rotation_y|rotation_z)\\($REAL\\)") { name: String?, r: String? ->
            when (name) {
                "rotation_x" -> Transform.rotationX(toNumber(r))
                "rotation_y" -> Transform.rotationY(toNumber(r))
                "rotation_z" -> Transform.rotationZ(toNumber(r))
                else -> fail("Invalid rotation: $name")
            }
        }

        ParameterType(
            "shearing",
            "shearing\\($REAL, $REAL, $REAL, $REAL, $REAL, $REAL\\)") {
                xy: String?, xz: String?, yx: String?, yz: String?, zx: String?, zy: String? ->
            Transform.shearing(
                toNumber(xy), toNumber(xz), toNumber(yx),
                toNumber(yz), toNumber(zx), toNumber(zy)
            )
        }

        // Ray
        ParameterType("ray", "ray\\(point\\($REAL, $REAL, $REAL\\), vector\\($REAL, $REAL, $REAL\\)\\)") {
                px: String?, py: String?, pz: String?,
                vx: String?, vy: String?, vz: String? ->
            val origin = Point(toNumber(px), toNumber(py), toNumber(pz))
            val direction = Vector(toNumber(vx), toNumber(vy), toNumber(vz))
            Ray(origin, direction)
        }

        // shapes
        ParameterType<Shape>("shape", "(sphere)\\(\\)") { name: String? ->
            when (name!!) {
                "sphere" -> Sphere()
                else -> fail("Invalid shape: $name")
            }
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

