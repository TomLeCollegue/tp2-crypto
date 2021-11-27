import java.awt.Color
import java.awt.image.BufferedImage


fun hideImage(image: BufferedImage, imageToHide: BufferedImage) {
    for (x in 0 until imageToHide.width) {
        for (y in 0 until imageToHide.height) {
            val color = Color(image.getRGB(x, y), true)
            val colorToHide = Color(imageToHide.getRGB(x, y), true)
            image.setRGB(x, y, color.fusionWithOtherPixel(colorToHide).rgb)
        }
    }
}

fun decodeImage(image: BufferedImage, width: Int, height: Int): BufferedImage {
    val result = BufferedImage(
        width, height, BufferedImage.TYPE_INT_ARGB
    )
    for (x in 0 until width) {
        for (y in 0 until height) {
            val color = Color(image.getRGB(x, y), true)
            result.setRGB(x,y, color.getColorHidden().rgb)
        }
    }
    return result
}

fun Color.getColorHidden(): Color {
    val red = this.red.extractHiddenInt()
    val green = this.green.extractHiddenInt()
    val blue = this.blue.extractHiddenInt()
    return Color(red, green, blue)
}

fun Int.extractHiddenInt(): Int {
    val binary = "${this.toByteWith8bits().takeLast(4)}0000"
    return Integer.parseInt(binary, 2)
}

fun Color.fusionWithOtherPixel(colorToHide: Color): Color {
    return Color(
        this.red.fusionWithOther(colorToHide.red),
        this.green.fusionWithOther(colorToHide.green),
        this.blue.fusionWithOther(colorToHide.blue),
    )
}

fun Int.fusionWithOther(intToHide: Int): Int {
    val newIntBinary = this.toByteWith8bits().substring(0, 4) +
            intToHide.toByteWith8bits().substring(0, 4)
    return Integer.parseInt(newIntBinary, 2)
}