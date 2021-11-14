import java.awt.Color
import java.awt.image.BufferedImage

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

fun Color.changeFirstBin(binNumber: Char) : Color {
    val redBin = red.toString(2).substring(0, 7) + binNumber
    val newRed = Integer.parseInt(redBin, 2)
    return Color(newRed, green, blue)
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