package shreyas.serviceInterface;



import shreyas.entities.userDetails;
import shreyas.requestResponseBean.fundTransferRequestBean;
import shreyas.requestResponseBean.loginRequestBean;
import shreyas.requestResponseBean.registrationRequestBean;


public interface serviceInterface 
{

	Object userRegistrationApi(registrationRequestBean user);

	Object userLoginApi(loginRequestBean user);


	

}
