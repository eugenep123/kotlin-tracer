package raytracer.cucumber.glue.shared

import io.cucumber.java8.En
import raytracer.films.Canvas
import raytracer.math.Color
import raytracer.math.*
import kotlin.math.sqrt

fun toDouble(s: String?): Double = when (s?.trim()) {
    "√2/2" -> sqrt(2.0) / 2.0
    else -> java.lang.Double.parseDouble(s)
}

fun toInt(s: String?): Int = java.lang.Integer.parseInt(s!!.trim())

class CommonGlue : En {
    init {

        ParameterType("vector", "vector\\((.+),(.+),(.+)\\)") { x: String?, y: String?, z: String? ->
            Vector(toDouble(x), toDouble(y), toDouble(z))
        }

        ParameterType("point", "point\\((.+),(.+),(.+)\\)") { x: String?, y: String?, z: String? ->
            Point(toDouble(x), toDouble(y), toDouble(z))
        }

        ParameterType(
            "color",
            "([a-z]+])|color\\((.+),(.+),(.+)\\)"
        ) { name: String?, r: String?, g: String?, b: String? ->
            name?.toColor() ?: Color(toDouble(r), toDouble(g), toDouble(b))
        }

        ParameterType("canvas", "canvas\\((.+),(.+)\\)") { width: String?, height: String? ->
            Canvas(toInt(width), toInt(height))
        }

        // Sqrt of some double
        ParameterType("sqrt", "√(.+)") { d: String? ->
            sqrt(toDouble(d))
        }
    }
}