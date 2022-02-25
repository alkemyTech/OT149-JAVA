package com.alkemy.ong.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;

    @Value("${jwt.validityHours}")
    private long TOKEN_VALIDITY_HOURS;

    /**
     * Extracts the username from token
     *
     * @param token the jwt token as String
     * @return the username as String
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from token
     *
     * @param token the jwt token as String
     * @return the expiration date as Date
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts specific information from token (e.g. subject or expiration)
     *
     * @param token          the jwt token as String
     * @param claimsResolver to get the required part of claimsResolver
     * @param <T>
     * @return the specific part
     */

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all the information from token using the secret key.
     *
     * @param token the jwt token as String
     * @return the Claims of token
     */

    private Claims extractAllClaims(String token) {

        return Jwts.parser().setSigningKey(this.SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /**
     * Check if the token has expired.
     *
     * @param token the jwt token as String
     * @return the boolean true if expiration date of token is before to currently date otherwise it is false.
     */

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Creates a token during sign in. This method is called from Authentication Controller.
     *
     * @param userDetails the data user who is sign in as UserDetails
     * @return the jwt token as String
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * While creating the token -
     * 1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
     * 2. Sign the JWT using the HS256 algorithm and secret key.
     * 3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
     * compaction of the JWT to a URL-safe string.
     *
     * @param claims  the Claims of token as Map
     * @param subject the subject as String
     * @return the token as String
     */

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * this.TOKEN_VALIDITY_HOURS)) // 1000*60*60*10 The token is valid for 10 hours
                .signWith(SignatureAlgorithm.HS256, this.SECRET_KEY).compact();
    }

    /**
     * Check if the token is valid. It's called from JwtRequestFilter
     *
     * @param token       the token as String
     * @param userDetails the user information as UserDetails
     * @return the boolean true if the token is valid otherwise is false
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
