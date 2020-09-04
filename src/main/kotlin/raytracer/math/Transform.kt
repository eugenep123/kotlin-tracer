package raytracer.math


data class Transform(val matrix: Matrix, val inv: Matrix) {

    val inverse: Transform by lazy { Transform(inv, matrix) }

    inline operator fun invoke(v: Vector): Vector = matrix * v
    inline operator fun invoke(p: Point): Point = matrix * p

    companion object {
        val Identity = Transform(Matrix.Identity, Matrix.Identity)

        fun translate(v: Vector): Transform = translate(v.x, v.y, v.z)
        fun translate(dx: Float, dy: Float, dz: Float): Transform =
            Transform(
                Matrix(
                    1.0f, 0.0f, 0.0f, dx,
                    0.0f, 1.0f, 0.0f, dy,
                    0.0f, 0.0f, 1.0f, dz,
                    0.0f, 0.0f, 1.0f, 1.0f
                ),
                Matrix(
                    1.0f, 0.0f, 0.0f, -dx,
                    0.0f, 1.0f, 0.0f, -dy,
                    0.0f, 0.0f, 1.0f, -dz,
                    0.0f, 0.0f, 1.0f, 1.0f
                )
            )


        fun scale(sv: Vector): Transform = scale(sv.x, sv.y, sv.z)
        fun scale(sx: Float, sy: Float, sz: Float): Transform =
            Transform(
                Matrix(
                    sx, 0.0f, 0.0f, 0.0f,
                    0.0f, sy, 0.0f, 0.0f,
                    0.0f, 0.0f, sz, 0.0f,
                    0.0f, 0.0f, 0.0f, 1.0f
                ),
                Matrix(
                    1.0f / sx, 0.0f, 0.0f, 0.0f,
                    0.0f, 1.0f / sy, 0.0f, 0.0f,
                    0.0f, 0.0f, 1.0f / sz, 0.0f,
                    0.0f, 0.0f, 0.0f, 1.0f
                )
            )
    }
}

