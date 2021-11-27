import java.io.File
import java.nio.charset.Charset
import javax.imageio.ImageIO

fun main() {
    // name of the target
    val nameTarget = "Le cerf de l'USMB"

    // Image hiding the contract
    val file = File("./chablais-orig.png")
    val image = ImageIO.read(file)

    // Image of the target
    val fileToHide = File("./cerf.jpeg")
    val imageToHide = ImageIO.read(fileToHide)

    // Hiding target image
    hideImage(image, imageToHide)

    // Opening private key
    val privateKey = "./privatekey".getByteArray().generatePrivateKeyFromRaw()

    // sign the target name
    val targetText = encrypt("#-- $nameTarget --#".toByteArray(), privateKey).toUByteArray()

    // hide the target name in the image
    hideTextInImage(image, text = targetText)

    // save the image with hidden info
    image.save("./contract.png")
    println("Contrat généré dans l'image contract.png")

}