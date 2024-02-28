package com.abigail.post.config.auth;


import com.example.postcard.service.UserService.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
//import java.util.logging.Logger;


/**
 * generate a JWT from email, date, expiration, secret
 * get email from JWT
 * validate a JWT
 */
@Component
public class JwtUtils {
    private final Logger logger = (Logger) LoggerFactory.getLogger(JwtUtils.class);

    @Value("${oat.app.jwtSecret}")
    private String jwtSecret;

    @Value("${oat.app.jwtExpirationMs:3000}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){
        UserDetailsImpl userDetailsPrincipal = (UserDetailsImpl )authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetailsPrincipal.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getEmailFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException exception){
            logger.error("Invalid JWT Token: {}", exception.getMessage());
        } catch (ExpiredJwtException exception){
            logger.error("Jwt Token is expired: {}", exception.getMessage());
        }catch (UnsupportedJwtException exception){
            logger.error("JWT Token is unssuported: {}", exception.getMessage());
        }catch (IllegalArgumentException exception){
            logger.error("JWT Certificate is wrong or empty: {}", exception.getMessage());
        }
        return false;
    }

//    public String getJwtFromCookies(HttpServletRequest request) {
//        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
//        if (cookie != null) {
//            return cookie.getValue();
//        } else {
//            return null;
//        }
//    }
//
//    public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
//        String jwt = generateJwtToken(userPrincipal.getUsername());
//        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
//        return cookie;
//    }
//
//    public ResponseCookie getCleanJwtCookie() {
//        ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
//        return cookie;
//    }
}
