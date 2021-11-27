import Constants.ALGORITHM
import Constants.CIPHER_TRANSFORMATION
import Constants.SIZE_KEY
import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher


object Constants {
    const val SIZE_KEY: Int = 2048
    const val CIPHER_TRANSFORMATION: String = "RSA/ECB/PKCS1Padding"
    const val ALGORITHM: String = "RSA"
}

fun createAsymmetricKeyPair(): KeyPair {
    val generator: KeyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM)
    generator.initialize(SIZE_KEY)
    return generator.generateKeyPair()
}

fun encrypt(data: ByteArray, publicKey: Key?): ByteArray {
    val cipher: Cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
    cipher.init(Cipher.ENCRYPT_MODE, publicKey)
    return cipher.doFinal(data)
}

fun decrypt(data: ByteArray, privateKey: Key?): String {
    val cipher: Cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
    cipher.init(Cipher.DECRYPT_MODE, privateKey)
    return String(cipher.doFinal(data))
}

fun ByteArray.generatePublicKeyFromRaw(): Key {
    val keyFactory: KeyFactory = KeyFactory.getInstance(ALGORITHM)
    return keyFactory.generatePublic(X509EncodedKeySpec(this))
}

fun ByteArray.generatePrivateKeyFromRaw(): Key {
    val keyFactory: KeyFactory = KeyFactory.getInstance(ALGORITHM)
    return keyFactory.generatePrivate(PKCS8EncodedKeySpec(this))
}


