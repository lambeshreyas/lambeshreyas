package shreyas.utils;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import shreyas.requestResponseBean.responseBean;

@Component
public class jwtClass 
{

	static String str="eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ0TW9nU2hKUXlYSlRSbmRyV2ZxZFot\n" + 
			"RVQ1S09jaUo1eXFHcExBa05VRGkwIn0.eyJleHAiOjE1OTk2NDMzNzQsImlhdCI6MTU5OTY0\n" + 
			"Mjc3NCwianRpIjoiOWU1YjExNmUtMTI4ZC00NGQ2LWIzMzctOWZjOTQyNzVhNjk4IiwiaXNzIjoi\n" + 
			"aHR0cDovL2VjMi01Mi0yMjEtMzMtMTE4LmFwLXNvdXRoZWFzdC0xLmNvbXB1dGUuYW1he\n" + 
			"m9uYXdzLmNvbTo4MDgyL2F1dGgvcmVhbG1zL3JlY29uY2lsaWF0aW9uIiwiYXVkIjoiYWNjb3V\n" + 
			"udCIsInN1YiI6Ijk1Yzg3YmJmLWRkYjAtNGIwMC05ZmQ1LTIyMjQ0YzkxZWJmOCIsInR5cCI6IkJ\n" + 
			"lYXJlciIsImF6cCI6InJlY29uY2lsaWF0aW9uLWNsaWVudCIsInNlc3Npb25fc3RhdGUiOiJkNTBkO\n" + 
			"DZiYi04NWExLTRkOTktYjgzNS1jZDkwMzdlNmM5YTQiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlna\n" + 
			"W5zIjpbIi8qIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXR\n" + 
			"ob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5\n" + 
			"hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2N\n" + 
			"vcGUiOiJwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZW\n" + 
			"RfdXNlcm5hbWUiOiJhc2luZ2giLCJlbWFpbCI6ImFzaW5naCJ9.ChGcCg_jIsx16eo8CgDpn0_BJ\n" + 
			"z-V2O9S_0suVVjL1uZauu8bX3bG-Jk8rjke8xNgrc4kr-7x6AxklAT1t2YIUI2L46UyWJ7pXOMJnlf4j\n" + 
			"04VzClHi9qDCuhrR3n71LiWQauPnZoeLoTqlAQhSbr78El8OmKen3o6hdHw94SjgWBQ3Hz3YV\n" + 
			"sbykYj2kXJO2Ey2pqgZS64ILyICm7wAslEZjPhh2sIHkLnCA9SGay4PNzInl3XVUuIzV3hCGxgUg\n" + 
			"6xWQmxXkZ7vEzvJ1Tc4g29xIulpUPn5wugNIMLUhFhSWOSNAmm2oJLKmZD0UCc0mJLwf2F\n" + 
			"2OrLfDzZbHh1eancig";
    
     
     
	public  String createJWT( String pass, String username) {
		
    	 System.out.println("username : "+username);
	    	
	    	SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    	 long nowMillis = System.currentTimeMillis();
		        Date now = new Date(nowMillis);

	   
	        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(str);
	        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	        JwtBuilder builder = Jwts.builder()
	        		.setIssuedAt(now)
	                .setSubject(pass)
	                .setIssuer(username)
	                .signWith(signatureAlgorithm, signingKey);
	      
	  /*  if (ttlMillis > 0) {
	        long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }  */
	  System.out.println("jwt : "+builder.compact());
	    return builder.compact();
	    
	    
	}
	
	    
	    
		public  Boolean decodeJWT(String jwt) {
			  Boolean val=true;
	    	
	      try { 
	    	
	    	  Claims claims = Jwts.parser()
	                .setSigningKey(DatatypeConverter.parseBase64Binary(str))
	                .parseClaimsJws(jwt).getBody();
	     
	              
	      
	        return val;
	      }
	      catch(Exception e)
	      {
	    	  val = false;
	    	    
	      }
		return val;
	    }
}
