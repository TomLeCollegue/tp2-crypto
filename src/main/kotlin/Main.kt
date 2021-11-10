import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    val file = File("./chablais-orig.png")
    val image = ImageIO.read(file)

    val text = "Je trouve ca impressionant comme truc c'est assez dingue de ouf"
    hideTextInImage(image, text = text)


    val newFile = File("./hideText.png")
    ImageIO.write(image, "png", newFile)

    val fileSecret = File("./hideText.png")
    val imageSecret = ImageIO.read(fileSecret)
    decodeTextFromImage(imageSecret, text.length)
}


fun hideTextInImage(image: BufferedImage, text: String) {
    val byteArray = text.toByteArray()
    byteArray.forEachIndexed { i, byte ->
        byte.toByteWith8bits().forEachIndexed { j, binNumber ->
            val pixel = image.getRGB(i, j)
            var color = Color(pixel, true)
            color = color.changeFirstBin(binNumber)
            image.setRGB(i, j, color.rgb)
        }
    }
}

fun Color.changeFirstBin(binNumber: Char) : Color{
    val redBin = red.toString(2)
    val newRed = redBin.substring(0, 7) + binNumber
    val lastRed = Integer.parseInt(newRed, 2)
    return Color(lastRed, green, blue)
}

fun decodeTextFromImage(image: BufferedImage, sizeText: Int) {
    var result = ""
    for (i in 0 until sizeText) {
        var charInBinary = ""
        for (j in 0..7) {
            val pixel = image.getRGB(i, j)
            val red = Color(pixel, true).red.toString(2)
            charInBinary += red.last()
        }
        result += Integer.parseInt(charInBinary, 2).toChar().toString()
    }
    println(result)
}

fun Byte.toByteWith8bits() : String{
    return "0000000${toString(2)}".takeLast(8)
}