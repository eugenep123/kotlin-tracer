package raytracer.utils

import raytracer.films.Canvas
import java.io.BufferedWriter
import java.io.File
import java.nio.file.Paths

fun outputFolder(): String {
    val path = Paths.get("").toAbsolutePath().toString()
    return "$path/output"
}

fun Canvas.writeToFolder(name: String = "canvas") {
    val folder = outputFolder()
    val timestamp = System.currentTimeMillis()
    val name = "$folder/${name}_$timestamp.ppm"
     writeToFile(name)
}
fun Canvas.writeToFile(fileName: String) {
    println("Saving canvas to: $fileName..")
    val ppm = this.toPpmList()
    File(fileName).bufferedWriter().use { out ->
        ppm.forEach {
            out.write(it)
            out.newLine()
        }
        out.newLine()
    }
}


