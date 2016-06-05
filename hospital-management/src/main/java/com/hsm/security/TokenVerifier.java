package com.hsm.security;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

public class TokenVerifier {

	private static String secretKey="HospitalMangement";
	static final Logger logger = Logger.getLogger(TokenVerifier.class);

	//Sample method to validate and read the JWT
	private void parseJWT(String jwt) {
		logger.debug("Entering the parseJWT method::"+jwt);
		//This line will throw an exception if it is not a signed JWS (as expected)
		Claims claims = Jwts.parser()         
				.setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
				.parseClaimsJws(jwt).getBody();
		logger.debug("End of parseJWT method::"+jwt);
	}

}
