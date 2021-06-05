package shreyas.Aspect;

import java.lang.reflect.Array;

import org.apache.commons.codec.binary.Base64;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;


import shreyas.requestResponseBean.responseBean;
import shreyas.utils.jwtClass;

@Component
@Aspect
@EnableAspectJAutoProxy
public class verifyAccessToken 
{
    @Autowired
	jwtClass jwt;
	
	@Around("execution(* shreyas.service.applicationService.*(..))")
	public  Object verifyAccessTokenmethod(ProceedingJoinPoint projp)
	{
		responseBean res =  new responseBean();
		
		System.out.println("hello i am aspect");

		try {
			Object[] oarray = projp.getArgs();

			String accessToken = (String) Array.get(oarray, 0);
		
			Boolean val = jwt.decodeJWT(accessToken);
			
			if(val.equals(false))
			{
				res.setResponseCode("401");
				res.setResponseDesc("Invalid AccessToken");
				return res;
			}
			
			
			return projp.proceed(oarray);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
