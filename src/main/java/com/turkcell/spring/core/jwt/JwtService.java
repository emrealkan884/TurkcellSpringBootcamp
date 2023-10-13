package com.turkcell.spring.core.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private long EXPIRATION = 86400000;

    //Bu metod, verilen JWT token'ından kullanıcı adını çıkarmak için kullanılır.
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //Bu metod, kullanıcı bilgileriyle birlikte bir JWT oluşturur.
    //Kullanıcı adı, veriliş tarihi, tokenin süresi (geçerlilik süresi) ve imza bilgileri JWT içine eklenir.
    public String generateToken(UserDetails user){
        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    //Bu metod, verilen JWT'nin geçerli olup olmadığını kontrol eder.
    public boolean isTokenValid(String token, UserDetails user){
        //İlk olarak, token içinden kullanıcı adını çıkarır (extractUsername metodunu kullanır)
        final String usernameFromToken = extractUsername(token);
        //ve bu kullanıcı adı ile parametre olarak gelen UserDetails nesnesinin kullanıcı adını karşılaştırır.
        return (user.getUsername().equals(usernameFromToken)) && !isTokenExpired(token);
    }

    //Bu metod, verilen JWT'nin süresinin dolup dolmadığını kontrol eder.
    public boolean isTokenExpired(String token){
        //bu tarihin şu anki tarihten önce olup olmadığını kontrol eder.
        return extractExpiration(token).before(new Date());
    }

    //Bu metod, verilen JWT'den son kullanma tarihini çıkarır.
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    //Bu genel metod, JWT içindeki talepleri ("claims") çıkarmak için kullanılır.
    //Function türü, giriş olarak bir Claims türünde değer alır ve çıkış olarak T türünde bir değer döndürür.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        //claims nesnesi içindeki talepleri belirli bir şekilde çıkarmak için claimsResolver fonksiyonunu kullanırız ve sonucu geri döneriz.
        return claimsResolver.apply(claims);
    }

    //Bu metod, JWT içindeki tüm talepleri alır.
    //Jwts sınıfının yardımıyla, JWT içeriğini çözümler ve içerisindeki talepleri alır.
    public Claims extractAllClaims(String token)
    {
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody(); // Jwt içerisindeki datayı parse eder.
    }

    //Bu metod, JWT'nin imzalanması için kullanılan gizli anahtarın alınmasını sağlar.
    //Anahtar, BASE64 kodlanmış gizli anahtar bilgisini çözerek
    //ve Keys sınıfıyla HMAC SHA algoritması için uygun bir anahtar nesnesi döndürerek elde edilir.
    public Key getSigninKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    //"Claim" (talep), bir JWT (JSON Web Token) içinde bulunan verilerin her bir parçasını ifade eder.
    // JWT, bilgileri veriler adı verilen talepler (claims) altında taşır.
    /* Bu örnek, bir JWT'nin içinde bulunan talepleri temsil eder.
        {
            "iss": "example.com",
            "sub": "1234567890",
            "exp": 1634296544,
            "name": "John Doe"
        }
    */
}