package sbnz.integracija.example.util;

import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
	private final int EXPIRES_IN = 3000000;
	
	@Value("juubi")
	private String secret;
	private final String AUTH_HEADER = "Authorization";
	
	
	public String generateJwt(UserClaims claims) {
		JwtBuilder builder = Jwts.builder();
		builder.setIssuedAt(new Date())
			   .setExpiration(generateExpirationDate())
			   .signWith(SIGNATURE_ALGORITHM, this.secret);
		for(String claim : claims.getClaims())
			builder.claim(claim, claims.getClaimValue(claim));
		return builder.compact();
	}
	
	public UserClaims decodeJwt(String jwt, Set<String> claims) {
		Claims allClaims = getAllClaimsFromToken(jwt);
		UserClaims userClaims = new UserClaims();
		for(String claim : claims)
			userClaims.setClaimValue(claim, allClaims.get(claim).toString());
		return userClaims;
	}
	
	public boolean expired(String jwt) {
		Date expires = getExpirationDate(jwt);
		if(expires != null)
			return expires.before(new Date());
		else
			return true;
	}
	
	public String getJwtFromRequest(HttpServletRequest request) {
		String authHeader = getAuthHeaderFromHeader(request);
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		return null;
	}

	private String getAuthHeaderFromHeader(HttpServletRequest request) {
		return request.getHeader(AUTH_HEADER);
	}
	
	private Date generateExpirationDate() {
		return new Date(new Date().getTime() + EXPIRES_IN);
	}
	
	private Claims getAllClaimsFromToken(String jwt) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(this.secret)
					.parseClaimsJws(jwt)
					.getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}
	
	private Date getExpirationDate(String jwt) {
		Date expiration;
		try {
			final Claims claims = this.getAllClaimsFromToken(jwt);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}
}
