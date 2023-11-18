package com.library.Library.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.xml.crypto.Data;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private final static String ACCESS_TOKEN_SECRET = "v3GUet4XTu705Is3MJc66rIl36uMBr35";
    private final static long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;


    public static String createToken(String username){
        long expTime = ACCESS_TOKEN_VALIDITY_SECONDS*1_000;
        Date expDate = new Date(System.currentTimeMillis() + expTime);

        /*Map<String, Object> extra = new HashMap<>();
        extra.put("username", username);*/

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expDate)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String tk){
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(tk)
                    .getBody();

            String username = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
        }catch (JwtException e){
            return null;
        }
    }
}
