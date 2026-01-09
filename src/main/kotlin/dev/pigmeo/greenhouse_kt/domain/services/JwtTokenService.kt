package dev.pigmeo.greenhouse_kt.domain.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class JwtTokenService(
    private val jwtEncoder: JwtEncoder,
    @param:Value("\${greenhouse.jwt.expiration-seconds}")
    private val expirationSeconds: Long,
) {
    fun generateToken(authentication: Authentication): String {
        val now = Instant.now()
        val scope = authentication.authorities.joinToString(separator = " ") { it.authority }

        val claims = JwtClaimsSet.builder()
            .issuer(ISSUER)
            .issuedAt(now)
            .expiresAt(now.plusSeconds(this.expirationSeconds))
            .subject(authentication.name)
            .claim("scope", scope)
            .build()

        val encoderParameters = JwtEncoderParameters.from(
            JwsHeader.with(MacAlgorithm.HS256).build(), claims
        )
        return this.jwtEncoder.encode(encoderParameters).tokenValue
    }

    companion object {
        private const val ISSUER = "dev.pigmeo.greenhouse"
    }

}