import java.io.File
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    val file = File("./chablais-orig.png")
    val image = ImageIO.read(file)

    val fileToHide = File("./cerf.jpeg")
    val imageToHide = ImageIO.read(fileToHide)

    hideImage(image, imageToHide)
    image.save("./hideImage.png")

    val fileSecret = File("./hideImage.png")
    val imageSecret = ImageIO.read(fileSecret)
    val result = decodeImage(imageSecret, 250, 225)
    result.save("./result.png")

}

