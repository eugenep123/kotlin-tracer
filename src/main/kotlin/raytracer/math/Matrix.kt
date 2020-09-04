package raytracer.math


// Array based matrix
//typealias MatrixA = Array<FloatArray>
typealias Matrix = Matrix4

data class Matrix4(
    val m00: Float, val m01: Float, val m02: Float, val m03: Float,
    val m10: Float, val m11: Float, val m12: Float, val m13: Float,
    val m20: Float, val m21: Float, val m22: Float, val m23: Float,
    val m30: Float, val m31: Float, val m32: Float, val m33: Float
) {

    val rows: Int
        get() = 4

    val columns: Int
        get() = 4

    companion object {
        fun of(a: Array<FloatArray>) =
            Matrix4(
                a[0][0], a[0][1], a[0][2], a[0][3],
                a[1][0], a[1][1], a[1][2], a[1][3],
                a[2][0], a[2][1], a[2][2], a[2][3],
                a[3][0], a[3][1], a[3][2], a[3][3]
            )

        fun from(f: (Int, Int) -> Float): Matrix4 =
            Matrix4(
                f(0, 0), f(0, 1), f(0, 2), f(0, 3),
                f(1, 0), f(1, 1), f(1, 2), f(1, 3),
                f(2, 0), f(2, 1), f(2, 2), f(2, 3),
                f(3, 0), f(3, 1), f(3, 2), f(3, 3)
            )
        val Identity: Matrix4 = from { x, y -> if (x == y) 1.0f else 0.0f }
    }
    inline operator fun get(row: Int, col: Int): Float = when(row) {
        0 -> when(col) {
            0 -> m00
            1 -> m01
            2 -> m02
            3 -> m03
            else -> throw IllegalArgumentException("col must be in 0..3")
        }
        1 -> when(col) {
            0 -> m10
            1 -> m11
            2 -> m12
            3 -> m13
            else -> throw IllegalArgumentException("col must be in 0..3")
        }
        2 -> when(col) {
            0 -> m20
            1 -> m21
            2 -> m22
            3 -> m23
            else -> throw IllegalArgumentException("col must be in 0..3")
        }
        3 -> when(col) {
            0 -> m30
            1 -> m31
            2 -> m32
            3 -> m33
            else -> throw IllegalArgumentException("col must be in 0..3")
        }
        else -> throw IllegalArgumentException("row must be in 0..3")
    }

}

inline operator fun Matrix4.times(other: Matrix4): Matrix4 =
    Matrix4(
    m00 * other.m00 + m01 * other.m10 + m02 * other.m20 + m03 * other.m30,
    m00 * other.m01 + m01 * other.m11 + m02 * other.m21 + m03 * other.m31,
    m00 * other.m02 + m01 * other.m12 + m02 * other.m22 + m03 * other.m32,
    m00 * other.m03 + m01 * other.m13 + m02 * other.m23 + m03 * other.m33,
    m10 * other.m00 + m11 * other.m10 + m12 * other.m20 + m13 * other.m30,
    m10 * other.m01 + m11 * other.m11 + m12 * other.m21 + m13 * other.m31,
    m10 * other.m02 + m11 * other.m12 + m12 * other.m22 + m13 * other.m32,
    m10 * other.m03 + m11 * other.m13 + m12 * other.m23 + m13 * other.m33,
    m20 * other.m00 + m21 * other.m10 + m22 * other.m20 + m23 * other.m30,
    m20 * other.m01 + m21 * other.m11 + m22 * other.m21 + m23 * other.m31,
    m20 * other.m02 + m21 * other.m12 + m22 * other.m22 + m23 * other.m32,
    m20 * other.m03 + m21 * other.m13 + m22 * other.m23 + m23 * other.m33,
    m30 * other.m00 + m31 * other.m10 + m32 * other.m20 + m33 * other.m30,
    m30 * other.m01 + m31 * other.m11 + m32 * other.m21 + m33 * other.m31,
    m30 * other.m02 + m31 * other.m12 + m32 * other.m22 + m33 * other.m32,
    m30 * other.m03 + m31 * other.m13 + m32 * other.m23 + m33 * other.m33)

inline operator fun Matrix.times(rhs: Point): Point =
    Point(
        m00 * rhs.x + m01 * rhs.y + m02 * rhs.z + m03,
        m10 * rhs.x + m11 * rhs.y + m12 * rhs.z + m13,
        m20 * rhs.x + m21 * rhs.y + m22 * rhs.z + m23)

inline operator fun Matrix4.times(rhs: Vector): Vector =
    Vector(
        m00 * rhs.x + m01 * rhs.y + m02 * rhs.z,
        m10 * rhs.x + m11 * rhs.y + m12 * rhs.z,
        m20 * rhs.x + m21 * rhs.y + m22 * rhs.z
    )

//data class Matrix(val array: Array<FloatArray>) {
//    operator fun get(row: Int, col: Int): Float = array[row][col]
//
//    val rows: Int
//        get() = array.size
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//        other as Matrix
//        if (!array.contentDeepEquals(other.array)) return false
//        return true
//    }
//
//    override fun hashCode(): Int {
//        return array.contentDeepHashCode()
//    }
//
//
//    companion object {
//        fun of(array: Array<FloatArray>) {
//
//        }
//    }
//
//}
