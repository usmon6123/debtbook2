package uz.ataboyev.debtbook.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import uz.ataboyev.debtbook.entity.Role;
import uz.ataboyev.debtbook.exception.RestException;
import uz.ataboyev.debtbook.exception.TokenExpiredException;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtProvider {
    @Value(value = "${accessTokenLifeTime}")
    private Long accessTokenLifeTime;
    //
    @Value(value = "${refreshTokenLifeTime}")
    private Long refreshTokenLifeTime;
    //
    @Value(value = "${tokenSecretKey}")
    private String tokenSecretKey;

    public String generateTokenFromId(UUID id, boolean isAccess) {
        Date expiredDate = new Date(System.currentTimeMillis() + (isAccess ? accessTokenLifeTime : refreshTokenLifeTime));
        return "Bearer " + Jwts
                .builder()
                .setSubject(id.toString())
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, tokenSecretKey)
                .compact();
    }
    public String generateTokenFromIdAndRoles(UUID id, Role role , boolean isAccess) {
        Date expiredDate = new Date(System.currentTimeMillis() + (isAccess ? accessTokenLifeTime : refreshTokenLifeTime));
        return "Bearer " + Jwts
                .builder()
                .setSubject(id.toString())
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .claim("roles",role.getName())
                .setSubject(id.toString())
                .signWith(SignatureAlgorithm.HS512, tokenSecretKey)
                .compact();
    }

    public String getIdFromToken(String token){
        try {
            return Jwts
                    .parser()
                    .setSigningKey(tokenSecretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch (ExpiredJwtException ex) {
            throw new TokenExpiredException();
        } catch (Exception e) {
            throw new RestException("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }
    public Claims getClaimsFromToken(String token){
        try {
            return Jwts
                    .parser()
                    .setSigningKey(tokenSecretKey)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (ExpiredJwtException ex) {
            throw new TokenExpiredException();
        } catch (Exception e) {
            throw new RestException("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(tokenSecretKey).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void validToken(String token) {
        Jwts.parser().setSigningKey(tokenSecretKey).parseClaimsJws(token);
    }


}
