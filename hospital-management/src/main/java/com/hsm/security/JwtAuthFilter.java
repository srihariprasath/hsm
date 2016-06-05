package com.hsm.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtAuthFilter extends GenericFilterBean{

	static final Logger logger = Logger.getLogger(JwtAuthFilter.class);
	private static String secretKey="HospitalMangement";

	@Override
	public void doFilter(final ServletRequest req,
			final ServletResponse res,
			final FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		logger.debug("Start DOFILTER method");
		if(!request.getMethod().equals("OPTIONS"))
		{
			String authHeader = request.getHeader("Authorization");

			if (authHeader == null || !authHeader.startsWith("Bearer")) {
				logger.error("Missing or invalid Authorization header");
				throw new ServletException("Missing or invalid Authorization header.");
			}

			final String token = authHeader.substring(7); // The part after "Bearer "

			try {
				final Claims claims = Jwts.parser().setSigningKey(secretKey)
						.parseClaimsJws(token).getBody();
				request.setAttribute("claims", claims);
			}
			catch (final SignatureException e) {
				throw new ServletException("Invalid token.");
			}
		}
		logger.debug("End of DOFILTER method");
		chain.doFilter(req, res);
	}

}
