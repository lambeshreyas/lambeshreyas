package shreyas.utils;

import org.springframework.stereotype.Component;

@Component
public class Constant 
{
 public long gernateTenDigitNumber()
 {
	 long number = (long) Math.floor(Math.random() * 900000000) + 1000000000;
	return number;

 }
}
