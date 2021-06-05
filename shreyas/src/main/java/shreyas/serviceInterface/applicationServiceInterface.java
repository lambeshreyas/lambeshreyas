package shreyas.serviceInterface;

import shreyas.requestResponseBean.fetchTransactionRequest;
import shreyas.requestResponseBean.fundTransferRequestBean;


public interface applicationServiceInterface {
	Object fundTransferApi(String accessToken, fundTransferRequestBean user);

	Object checkBalanceApi(String accessToken, fetchTransactionRequest user);

	Object showTransactionHistoryApi(String accessToken, fetchTransactionRequest user);
}
