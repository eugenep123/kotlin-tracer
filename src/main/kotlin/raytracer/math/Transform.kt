package raytracer.math


data class Transform(val matrix: Matrix, val inverseMatrix: Matrix) {

    val inverse: Transform by lazy { Transform(inverseMatrix, matrix) }

    inline operator fun invoke(v: Vector): Vector = matrix * v
    inline operator fun invoke(p: Point): Point = matrix * p
    inline operator fun invoke(r: Ray): Ray = r(matrix)

    inline operator fun times(t: Transform): Transform =
        Transform(matrix * t.matrix, inverseMatrix * t.inverseMatrix)

    companion object {
        fun of(m: Matrix): Transform? = m.inverse?.let { Transform(m, it) }
        val Identity = Transform(Matrix.Identity, Matrix.Identity)

        fun translate(v: Vector): Transform = translate(v.x, v.y, v.z)
        fun translate(dx: Double, dy: Double, dz: Double): Transform =
            Transform(Matrix.translate(dx, dy, dz), Matrix.translate(-dx, -dy, -dz))

        fun scale(sv: Vector): Transform = scale(sv.x, sv.y, sv.z)
        fun scale(sx: Double, sy: Double, sz: Double): Transform =
            Transform(
                Matrix.scale(sx, sy, sz),
                Matrix.scale(1.0 / sx, 1.0 / sy, 1.0 / sz)
            )

        fun rotationX(r: Radians): Transform =
            Transform(Matrix.rotateX(r), Matrix.rotateX(-r))

        fun rotationY(r: Radians): Transform =
            Transform(Matrix.rotateY(r), Matrix.rotateY(-r))

        fun rotationZ(r: Radians): Transform =
            Transform(Matrix.rotateZ(r), Matrix.rotateZ(-r))

        fun shearing(
            xy: Double, xz: Double,
            yx: Double, yz: Double,
            zx: Double, zy: Double
        ): Transform =
            Transform(
                Matrix.shearing(xy, xz, yx, yz, zx, zy),
                Matrix.shearing(1.0 / xy, 1.0 / xz, 1.0 / yx, 1.0 / yz, 1.0 / zx, 1.0 / zy)
            )

        fun view(from: Point, to: Point, up: Vector): Transform? =
            Transform.of(Matrix.view(from, to, up))
    }

}

