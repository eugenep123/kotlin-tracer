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
        fun translate(dx: Float, dy: Float, dz: Float): Transform =
            Transform(translateMatrix(dx, dy, dz), translateMatrix(-dx, -dy, -dz))

        fun scale(sv: Vector): Transform = scale(sv.x, sv.y, sv.z)
        fun scale(sx: Float, sy: Float, sz: Float): Transform =
            Transform(
                scaleMatrix(sx, sy, sz),
                scaleMatrix(1.0f / sx, 1.0f / sy, 1.0f / sz)
            )

        fun rotationX(r: Radians): Transform =
            Transform(rotateXMatrix(r), rotateXMatrix(-r))


    }
}

fun translateMatrix(dx: Float, dy: Float, dz: Float): Matrix =
    Matrix(
        1.0f, 0.0f, 0.0f, dx,
        0.0f, 1.0f, 0.0f, dy,
        0.0f, 0.0f, 1.0f, dz,
        0.0f, 0.0f, 1.0f, 1.0f
    )

fun rotateXMatrix(r: Radians): Matrix =
    Matrix(
        1.0f, 0.0f, 0.0f, 0.0f,
        0.0f, cos(r), -sin(r), 0.0f,
        0.0f, sin(r), cos(r), 0.0f,
        0.0f, 0.0f, 0.0f, 1.0f
    )

fun scaleMatrix(sx: Float, sy: Float, sz: Float): Matrix =
    Matrix(
        sx, 0.0f, 0.0f, 0.0f,
        0.0f, sy, 0.0f, 0.0f,
        0.0f, 0.0f, sz, 0.0f,
        0.0f, 0.0f, 0.0f, 1.0f
    )
