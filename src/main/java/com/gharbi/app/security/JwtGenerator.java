package com.gharbi.app.security;




import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.gharbi.app.model.JwtUser;

@Component
public class JwtGenerator {


    public String generate(JwtUser jwtUser) {


        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());

        
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiration_date(new Date(), 100))
                .signWith(SignatureAlgorithm.HS512, "gharbi")
                .compact();
    }
    
    public Date expiration_date(Date date, Integer seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
      }
}