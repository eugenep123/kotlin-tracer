package raytracer.math

import kotlin.math.cos
import kotlin.math.sin


data class Transform(val matrix: Matrix, val inverseMatrix: Matrix) {

    val inverse: Transform by lazy { Transform(inverseMatrix, matrix) }

    inline operator fun invoke(v: Vector): Vector = matrix * v
    inline operator fun invoke(p: Point): Point = matrix * p

    companion object {
        val Identity = Transform(Matrix.Identity, Matrix.Identity)

        fun translate(v: Vector): Transform = translate(v.x, v.y, v.z)
        fun translate(dx: Double, dy: Double, dz: Double): Transform =
            Transform(translateMatrix(dx, dy, dz), translateMatrix(-dx, -dy, -dz))

        fun scale(sv: Vector): Transform = scale(sv.x, sv.y, sv.z)
        fun scale(sx: Double, sy: Double, sz: Double): Transform =
            Transform(
                scaleMatrix(sx, sy, sz),
                scaleMatrix(1.0 / sx, 1.0 / sy, 1.0 / sz)
            )

        fun rotationX(r: Radians): Transform =
            Transform(rotateXMatrix(r), rotateXMatrix(-r))


    }
}

fun translateMatrix(dx: Double, dy: Double, dz: Double): Matrix =
    Matrix(
        1.0, 0.0, 0.0, dx,
        0.0, 1.0, 0.0, dy,
        0.0, 0.0, 1.0, dz,
        0.0, 0.0, 1.0, 1.0
    )

fun rotateXMatrix(r: Radians): Matrix =
    Matrix(
        1.0, 0.0, 0.0, 0.0,
        0.0, cos(r), -sin(r), 0.0,
        0.0, sin(r), cos(r), 0.0,
        0.0, 0.0, 0.0, 1.0
    )

fun scaleMatrix(sx: Double, sy: Double, sz: Double): Matrix =
    Matrix(
        sx, 0.0, 0.0, 0.0,
        0.0, sy, 0.0, 0.0,
        0.0, 0.0, sz, 0.0,
        0.0, 0.0, 0.0, 1.0
    )
