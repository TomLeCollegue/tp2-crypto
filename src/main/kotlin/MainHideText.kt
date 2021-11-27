import java.io.File
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    val file = File("./chablais-orig.png")
    val image = ImageIO.read(file)

    val text = "Je trouve ca impressionant comme truc c'est assez dingue de ouf"
    hideTextInImage(image, text = text)

    image.save("./hideText.png")

    val fileSecret = File("./hideText.png")
    val imageSecret = ImageIO.read(fileSecret)
    print(decodeTextFromImage(imageSecret, text.length))
}






