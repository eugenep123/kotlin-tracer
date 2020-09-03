package raytracer.math

import java.lang.Exception
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Color(val red: Float, val green: Float, val blue: Float) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Color
        return (red eq other.red) && (green eq other.green) &&  (blue eq other.blue)
    }
}

inline operator fun Color.plus(rhs: Color): Color =
    Color(red + rhs.red, green + rhs.green, blue + rhs.blue)

inline operator fun Color.minus(rhs: Color): Color =
    Color(red - rhs.red, green - rhs.green, blue - rhs.blue)

inline operator fun Color.times(d: Float): Color =
    Color(red * d, green * d, blue * d)

inline operator fun Color.times(rhs: Color): Color =
    Color(red * rhs.red, green * rhs.green, blue * rhs.blue)

private fun scaled(color: Float, scale: Int): Int {
    val abs = abs(min(scale, (max(0.0f, color) * scale + 0.5f).toInt()))
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
    val BLACK: Color = Color(0.0f, 0.0f, 0.0f)
    val RED: Color  = Color(1.0f, 0.0f, 0.0f)
    val GREEN: Color = Color(0.0f, 1.0f, 0.0f)
    val BLUE: Color = Color(0.0f, 0.0f, 1.0f)
    val WHITE: Color = Color(1.0f, 1.0f, 1.0f)
}