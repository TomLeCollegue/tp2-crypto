
fun main() {

    // generate RSA key pair
    val keypair = createAsymmetricKeyPair()

    // Save in files the keys
    keypair.private.saveToFile("./privatekey")
    keypair.public.saveToFile("./publickey")
}