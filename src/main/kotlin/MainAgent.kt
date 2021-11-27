import java.io.File
import javax.imageio.ImageIO

fun main() {
    // Load public key of the contractor
    val publicKey = "./publickey".getByteArray().generatePublicKeyFromRaw()

    // Open contract image
    val contractFile = File("./contract.png")
    val contractImage = ImageIO.read(contractFile)

    // Get image of the target hidden in the image and save it in target.png
    val targetImage = decodeImage(contractImage, 250, 225)
    targetImage.save("./target.png")

    // Verify the authenticity and display target name
    val targetNameEncoded = decodeTextFromImageByteArray(contractImage, 256)
    val decodedName = decrypt(targetNameEncoded, publicKey)
    if (decodedName.startsWith("#--") && decodedName.endsWith("--#")) {
        println("Contrat officiel verifié")
        println(decodedName)
        println("retrouvez le portrait de la cible dans target.png")
    }

}