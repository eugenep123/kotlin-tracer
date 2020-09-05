package raytracer.shapes


data class Intersection(
    val time: Double,
    val shape: Shape)


val List<Intersection>.sorted: List<Intersection>
    get() = this.sortedBy { it.time }

fun List<Intersection>.onHit(action: (Intersection) -> Unit): Unit {
    this.find { it.time > 0 } ?.let(action)
}

//data class Intersection(val primitive: Primitive, val time: Double)
