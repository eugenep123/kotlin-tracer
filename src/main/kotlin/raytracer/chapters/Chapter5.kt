package raytracer.chapters

import raytracer.films.Canvas
import raytracer.math.*
import raytracer.shapes.Shape
import raytracer.shapes.Sphere
import raytracer.shapes.onHit
import raytracer.shapes.sorted
import raytracer.utils.writeToFolder

fun main(args: Array<String>) {

    for (type in 0 .. 4) {
        val sphere = makeSphere(type)
        renderShape(sphere)
    }

}


fun renderShape(shape: Shape) {
    val rayOrigin = Point(0.0, 0.0, -5.0)
    val wallZ = 10.0
    val wallSize = 7.0
    val canvasPixels = 400
    val pixelSize = wallSize / canvasPixels
    val half = wallSize / 2

    val canvas = Canvas(canvasPixels, canvasPixels)
    val red = Color(1.0, 0.0, 0.0)

    for (y in 0 .. canvasPixels) {
        for (x in 0 .. canvasPixels) {
// compute the world x coordinate (left = -half, right = half)
            val worldX = -half + pixelSize * x
            // compute the world y coordinate (top = +half, bottom = -half)
            val worldY = half - pixelSize * y
            // describe the Point3D on the wall that the ray will target
            val position = Point(worldX, worldY, wallZ)
            val substractedPosition = position - rayOrigin
            val normalizedPosition = substractedPosition.normalize
            val ray = Ray(rayOrigin, normalizedPosition)

            val xs = shape.intersect(ray).sorted
            xs.onHit { canvas[x, y] = red }
        }
    }

    canvas.writeToFolder("chapter5")


}

fun makeSphere(type: Int): Shape =
    when(type) {
        // borin sphere
        0 -> Sphere()
        // shrink it along the y axis
        1 -> Sphere(Transform.scale(1.0, 0.5, 1.0))
        // shrink it along the x-axis
        2 -> Sphere(Transform.scale(0.5, 1.0, 1.0))
        // shrink it, and rotate it!
        3 -> Sphere(Transform.rotationZ(PI / 4.0) * Transform.scale(0.5, 1.0, 1.0))
        // shrink it, and skew it!
        else -> Sphere(Transform.shearing(1.0, 0.0, 0.0, 0.0, 0.0, 0.0) * Transform.scale(0.5, 1.0, 1.0))
    }