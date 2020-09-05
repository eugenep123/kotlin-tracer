package raytracer.math


data class Transform(val matrix: Matrix, val inverseMatrix: Matrix, val info: String) {

    val inverse: Transform by lazy { Transform(inverseMatrix, matrix, "inverse: $info") }

    inline operator fun invoke(v: Vector): Vector = matrix * v
    inline operator fun invoke(p: Point): Point = matrix * p
    inline operator fun invoke(r: Ray): Ray = r(matrix)

    inline operator fun times(t: Transform): Transform =
        Transform(matrix * t.matrix, t.inverseMatrix * inverseMatrix, "($info) * (${t.info})")

    companion object {
        fun of(m: Matrix, info: String = "matrix"): Transform? = m.inverse?.let { Transform(m, it, info) }
        val Identity = Transform(Matrix.Identity, Matrix.Identity, "Identity")

        fun translate(v: Vector): Transform = translate(v.x, v.y, v.z)
        fun translate(dx: Double, dy: Double, dz: Double): Transform =
            Transform(Matrix.translate(dx, dy, dz), Matrix.translate(-dx, -dy, -dz), "translate: ($dx, $dy, $dz)")

        fun scale(sv: Vector): Transform = scale(sv.x, sv.y, sv.z)
        fun scale(sx: Double, sy: Double, sz: Double): Transform =
            Transform(
                Matrix.scale(sx, sy, sz),
                Matrix.scale(1.0 / sx, 1.0 / sy, 1.0 / sz),
                "scale: ($sx, $sy, $sz)"
            )

        fun rotationX(r: Radians): Transform =
            Transform(Matrix.rotateX(r), Matrix.rotateX(-r), "rotate_x: $r")

        fun rotationY(r: Radians): Transform =
            Transform(Matrix.rotateY(r), Matrix.rotateY(-r), "rotate_y: $r")

        fun rotationZ(r: Radians): Transform =
            Transform(Matrix.rotateZ(r), Matrix.rotateZ(-r), "rotate_z: $r")

        fun shearing(
            xy: Double, xz: Double,
            yx: Double, yz: Double,
            zx: Double, zy: Double): Transform =
            Transform(
                Matrix.shearing(xy, xz, yx, yz, zx, zy),
                Matrix.shearing(1.0 / xy, 1.0 / xz, 1.0 / yx, 1.0 / yz, 1.0 / zx, 1.0 / zy),
                "rotate_z: $xy, $xz, $yx, $yz, $zx, $zy"
            )

        fun view(from: Point, to: Point, up: Vector): Transform? =
            Transform.of(Matrix.view(from, to, up), "view ($from, $to, $up)")
    }

}

