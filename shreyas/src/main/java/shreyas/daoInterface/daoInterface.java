package shreyas.daoInterface;



import shreyas.entities.accountDetails;
import shreyas.entities.transactionDetails;
import shreyas.entities.userDetails;
import shreyas.requestResponseBean.fundTransferRequestBean;
import shreyas.requestResponseBean.loginRequestBean;
import shreyas.requestResponseBean.registrationRequestBean;

public interface daoInterface {

	Object addUserDetails(userDetails user);

	Object addAcoountDeatils(accountDetails user);

	Object userLoginApi(loginRequestBean user);

	public Object UpdateaccountBalance(double balnce, long accountNumber);

	Object fundTransfer(transactionDetails transaction);

	Object getAccountByNumberApi(long accountNumber);
	

}
