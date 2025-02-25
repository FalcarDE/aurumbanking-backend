package de.fhe.cc.team4.aurumbanking.core

import io.smallrye.jwt.build.Jwt
import jakarta.enterprise.context.ApplicationScoped
import java.nio.charset.Charset
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*


@ApplicationScoped
class JwtTokenUtils {
    companion object {

        @Throws(Exception::class)
        fun generateToken(username: String, role: String ,duration: Long, issuer: String): String {
            val privateKeyLocation = "/privateKey.pem"
            val privateKey = readPrivateKey(privateKeyLocation)
            val claimsBuilder = Jwt.claims()
            val currentTimeInSecs = currentTimeInSecs().toLong()
            val groups: MutableSet<String> = HashSet()
            groups.add(role)
            claimsBuilder.issuer(issuer)
            claimsBuilder.subject(username)
            claimsBuilder.issuedAt(currentTimeInSecs)
            claimsBuilder.expiresAt(currentTimeInSecs + duration)
            claimsBuilder.groups(groups)
            claimsBuilder.claim("level-of-nerdiness", 42)
            return claimsBuilder.jws().sign(privateKey)
        }

        @Throws(Exception::class)
        private fun readPrivateKey(pemResName: String?): PrivateKey {
            JwtTokenUtils::class.java.getResourceAsStream(pemResName).use { contentIS ->
                val tmp = ByteArray(4096)
                val length: Int = contentIS.read(tmp)
                return decodePrivateKey(String(tmp, 0, length, Charset.defaultCharset()))
            }
        }

        @Throws(Exception::class)
        private fun decodePrivateKey(pemEncoded: String): PrivateKey {
            val encodedBytes = toEncodedBytes(pemEncoded)
            val keySpec = PKCS8EncodedKeySpec(encodedBytes)
            val kf = KeyFactory.getInstance("RSA")
            return kf.generatePrivate(keySpec)
        }

        private fun toEncodedBytes(pemEncoded: String): ByteArray {
            val normalizedPem = removeBeginEnd(pemEncoded)
            return Base64.getDecoder().decode(normalizedPem)
        }

        private fun removeBeginEnd(pemString: String): String {
            var pem = pemString
            pem = pem.replace("-----BEGIN (.*)-----".toRegex(), "")
            pem = pem.replace("-----END (.*)----".toRegex(), "")
            pem = pem.replace("\r\n".toRegex(), "")
            pem = pem.replace("\n".toRegex(), "")
            return pem.trim { it <= ' ' }
        }

        private fun currentTimeInSecs(): Int {
            val currentTimeMS = System.currentTimeMillis()
            return (currentTimeMS / 1000).toInt()
        }
    }

}
