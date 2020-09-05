package raytracer.films

data class Pixel(val x: Int, val y: Int) : Comparable<Pixel> {
    override fun compareTo(other: Pixel): Int {
        if (this.y > other.y) {
            return this.y.compareTo(other.y)
        }
        return this.x.compareTo(other.x)
    }
}
//operator fun Pixel.rangeTo(that: Pixel): ClosedRange<Pixel> {
//
//}