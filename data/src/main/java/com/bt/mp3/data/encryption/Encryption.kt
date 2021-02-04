package com.bt.mp3.data.encryption

import com.bt.mp3.data.model.SignatureEntity
import java.math.BigInteger
import java.security.MessageDigest
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Encryption @Inject constructor() {

    companion object {
        const val SECRET_KEY = "882QcNXV4tUZbvAsjmFOHqNC1LpcBRKW"
    }

    fun getSignature(path: String, param: String): SignatureEntity {
        val time = System.currentTimeMillis() / 1000
        val hash256 = toHexString(getSHA("ctime=${time}$param"))

        return SignatureEntity(
            time = time,
            sig = getSecurePassword(path + hash256, SECRET_KEY) ?: ""
        )
    }

    private fun getSecurePassword(password: String, salt: String, algo: String = "HmacSHA512"): String? {
        val secretKeySpec = SecretKeySpec(salt.toByteArray(), algo)
        val mac = Mac.getInstance(algo)
        mac.init(secretKeySpec)
        val bytes = mac.doFinal(password.toByteArray())
        return BigInteger(1, bytes).toString(16)
    }

    private fun getSHA(input: String): ByteArray? {
        // Static getInstance method is called with hashing SHA
        val md = MessageDigest.getInstance("SHA-256")

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.toByteArray())
    }

    private fun toHexString(hash: ByteArray?): String? {
        // Convert byte array into signum representation
        val number = BigInteger(1, hash)

        // Convert message digest into hex value
        val hexString = StringBuilder(number.toString(16))

        // Pad with leading zeros
        while (hexString.length < 32) {
            hexString.insert(0, '0')
        }
        return hexString.toString()
    }
}
