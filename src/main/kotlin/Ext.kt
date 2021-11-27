import java.awt.image.BufferedImage
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.security.Key
import javax.imageio.ImageIO


fun Byte.toByteWith8bits() : String{
    return "0000000${toString(2)}".takeLast(8)
}

fun UByte.toByteWith8bits() : String{
    return "0000000${toString(2)}".takeLast(8)
}

fun Int.toByteWith8bits() : String{
    return "0000000${toString(2)}".takeLast(8)
}


fun BufferedImage.save(path: String) {
    val newFile = File(path)
    ImageIO.write(this, "png", newFile)
}

fun Key.saveToFile(path: String) {
    FileOutputStream(path).use {
            stream -> stream.write(this.encoded)
    }
}

fun String.getByteArray(): ByteArray {
    // read private key DER file
    // read private key DER file
    val dis = DataInputStream(FileInputStream(this))
    val privKeyBytes = ByteArray(File(this).length().toInt())
    dis.read(privKeyBytes)
    dis.close()
    return privKeyBytes
}