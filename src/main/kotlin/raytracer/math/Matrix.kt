package raytracer.math


// Array based matrix
//typealias MatrixA = Array<FloatArray>

data class Matrix(val array: Array<FloatArray>) {
    operator fun get(row: Int, col: Int): Float = array[row][col]

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Matrix
        if (!array.contentDeepEquals(other.array)) return false
        return true
    }

    override fun hashCode(): Int {
        return array.contentDeepHashCode()
    }
}



//data class Matrix4(
//    m00: Float, m01: Float, m02: Float, m03: Float,
//    m10: Float, m11: Float, m12: Float, m13: Float,
//    m20: Float, m21: Float, m22: Float, m23: Float,
//    m30: Float, m31: Float, m32: Float, m33: Float
//) {
//
//}

