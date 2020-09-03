package raytracer.math

import java.lang.Exception
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Color(val red: Double, val green: Double, val blue: Double) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Color
        return (red eq other.red) &&  (blue eq other.blue) && (green eq other.green)
    }
}

inline operator fun Color.plus(rhs: Color): Color =
    Color(red + rhs.red, green + rhs.green, blue + rhs.blue)

inline operator fun Color.minus(rhs: Color): Color =
    Color(red - rhs.red, green - rhs.green, blue - rhs.blue)

inline operator fun Color.times(d: Double): Color =
    Color(red * d, green * d, blue * d)

inline operator fun Color.times(rhs: Color): Color =
    Color(red * rhs.red, green * rhs.green, blue * rhs.blue)

private fun scaled(color: Double, scale: Int): Int {
    val abs = abs(min(scale, (max(0.0, color) * scale + 0.5).toInt()))
    if(abs > scale) {
        println("!!! WARNING > $scale $abs")
    }
    return abs
}

fun String.toColor(): Color =
    when(this) {
        "red" -> Colors.RED
        "green" -> Colors.GREEN
        "blue" -> Colors.BLUE
        "black" -> Colors.BLACK
        "white" -> Colors.WHITE
        else -> throw Exception("Invalid color: $this")
    }

fun Color.toRgb(scale: Int = 255): String {
    val r = scaled(red, scale)
    val g = scaled(green, scale)
    val b = scaled(blue, scale)
    return "$r $g $b"
}

object Colors {
    val BLACK: Color = Color(0.0, 0.0, 0.0)
    val RED: Color = Color(1.0, 0.0, 0.0)
    val GREEN: Color = Color(0.0, 1.0, 0.0)
    val BLUE: Color = Color(0.0, 0.0, 1.0)
    val WHITE: Color = Color(1.0, 1.0, 1.0)
}