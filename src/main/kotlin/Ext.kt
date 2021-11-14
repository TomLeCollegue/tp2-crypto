import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun Byte.toByteWith8bits() : String{
    return "0000000${toString(2)}".takeLast(8)
}

fun Int.toByteWith8bits() : String{
    return "0000000${toString(2)}".takeLast(8)
}


fun BufferedImage.save(path: String) {
    val newFile = File(path)
    ImageIO.write(this, "png", newFile)
}