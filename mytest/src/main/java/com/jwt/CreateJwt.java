package com.jwt;

import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class CreateJwt {
//	private final static String secret = "fj32Jfv02Mq33g0f8ioDkw";
//
//    public static String createToken(String email)
//    {
//        try {
//            return JWT.create()
//                    .withIssuer("auth0")
//                    .withClaim("email", email)
//                    .sign(Algorithm.HMAC256(secret));
//        } catch (JWTCreationException exception){
//            throw new RuntimeException("You need to enable Algorithm.HMAC256");
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    public static String getEmailInToken(String token)
//    {
//        try {
//            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
//                    .withIssuer("auth0")
//                    .build();
//            DecodedJWT jwt = verifier.verify(token);
//            return jwt.getClaim("email").asString();
//        } catch (JWTDecodeException exception){
//            return null;
//        } catch (UnsupportedEncodingException e) {
//            return null;
//        }
//    }
//    
//    public static void main(String[] args) {
//		
//    	String token = createToken("393941932@qq.com");
//    	System.out.println(token);
//    	
//    	String token2 = getEmailInToken(token);
//    	System.out.println(token2);
//	}
	
	public static String create() {
		
//		Key key = MacProvider.generateKey();
		String key = "asdf";
		
		String compactJws = Jwts.builder()
				.setSubject("Joe")
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();
		
//		Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey("asdf").parseClaimsJws(compactJws);
		return compactJws;
	}
	
	public static void main(String[] args) {
		String create = create();
		System.out.println(create);
	}
}
