package shreyas.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shreyas.daoInterface.daoInterface;
import shreyas.entities.accountDetails;
import shreyas.entities.transactionDetails;
import shreyas.requestResponseBean.checkBalanceResponseBean;
import shreyas.requestResponseBean.fetchTransactionRequest;
import shreyas.requestResponseBean.fundTransferRequestBean;
import shreyas.requestResponseBean.fundTransferResponse;
import shreyas.requestResponseBean.responseBean;
import shreyas.requestResponseBean.transactionHistoryResponse;
import shreyas.requestResponseBean.transactionResponse;
import shreyas.serviceInterface.applicationServiceInterface;
import shreyas.utils.Constant;

@Service
@Transactional
public class applicationService implements applicationServiceInterface{

	@Autowired
	daoInterface dao;
	
	@Autowired
	Constant con;
	
	@Override
	public Object fundTransferApi(String accessToken, fundTransferRequestBean user) {
		// TODO Auto-generated method stub
		responseBean response = new responseBean();
		response.setResponseCode("00");
		response.setResponseDesc("Fund Successfully Transfered");
		//double balanceDebitAccount = (Double) dao.BalnceUpdate(user);
		List<accountDetails> accountDebit = (List<accountDetails>) dao.getAccountByNumberApi(user.getFromAccount());
		List<accountDetails> accountCredit = (List<accountDetails>) dao.getAccountByNumberApi(user.getToAccount());
			
			if( accountDebit.size() > 0 && accountCredit.size() > 0 )
			{
				
				if(accountDebit.get(0).getAvailable_balance() >= user.getAmount())
				{
					double debitBalance = accountDebit.get(0).getAvailable_balance() - user.getAmount();
					double creditBalance = accountCredit.get(0).getAvailable_balance() + user.getAmount();

					long TransactionReferanceNumber = con.gernateTenDigitNumber();
					transactionDetails transactionCR = new transactionDetails();
					transactionCR.setAccount_no(accountCredit.get(0));
					transactionCR.setTransaction_amount(user.getAmount());
					transactionCR.setTransaction_flag("CR");
					transactionCR.setReferance_number(TransactionReferanceNumber);
					dao.fundTransfer(transactionCR);
					
					transactionDetails transactionDR = new transactionDetails();
					transactionDR.setAccount_no(accountDebit.get(0));
					transactionDR.setTransaction_amount(user.getAmount());
					transactionDR.setTransaction_flag("DR");
					transactionDR.setReferance_number(TransactionReferanceNumber);
					dao.fundTransfer(transactionDR);
					dao.UpdateaccountBalance(debitBalance,accountDebit.get(0).getAccount_no());
					dao.UpdateaccountBalance(creditBalance,accountCredit.get(0).getAccount_no());
					fundTransferResponse res = new fundTransferResponse();
					res.setAccountBalnce(debitBalance);
					res.setReferanceNumber(TransactionReferanceNumber);
					response.setData(res);
				}
				else
				{
					response.setResponseCode("401");
					response.setResponseDesc("Debit account has less Balance than Reqirement");
				
			    }
		}
			else
			{
				response.setResponseCode("401");
				response.setResponseDesc("Invalid Account Detals");
			}
		
		return response;
	}

	@Override
	public Object checkBalanceApi(String accessToken, fetchTransactionRequest user) {
		// TODO Auto-generated method stub
		responseBean response = new responseBean();
		response.setResponseCode("00");
		response.setResponseDesc("Invalid account Details");
		try
		{
			List<accountDetails> accountCredit = (List<accountDetails>) dao.getAccountByNumberApi(user.getAccountNumber());
			
			if(accountCredit.size() > 0)
			{
				checkBalanceResponseBean res = new checkBalanceResponseBean();
				res.setAccountBalance(accountCredit.get(0).getAvailable_balance());
				
				response.setResponseCode("00");
				response.setResponseDesc("Balance Successfully fetch");
				response.setData(res);
			}
		}
		catch(Exception e)
		{
			response.setResponseCode("401");
			response.setResponseDesc("Error in a code");
		}
		
		
		return response;
	}

	@Override
	public Object showTransactionHistoryApi(String accessToken, fetchTransactionRequest user) {
		// TODO Auto-generated method stub
		responseBean response = new responseBean();
		response.setResponseCode("401");
		response.setResponseDesc("Invalid Accounts Details");
		List<accountDetails> account = (List<accountDetails>) dao.getAccountByNumberApi(user.getAccountNumber());
		
		if(account.size() > 0 )
		{

			if(!(account.get(0).getTransaction().isEmpty()))
			{
				List<transactionDetails> transList = account.get(0).getTransaction() ;
				ArrayList<transactionHistoryResponse> trasres = new ArrayList<transactionHistoryResponse>();
				for (transactionDetails bean : transList)
				{
					transactionHistoryResponse res = new transactionHistoryResponse();
					res.setAmount(bean.getTransaction_amount());
					res.setReferenceNumber(bean.getReferance_number());
					res.setTransactionflag(bean.getTransaction_flag());
					trasres.add(res);
				}
				transactionResponse res = new transactionResponse();
				res.setTransaction(trasres);
				
				response.setResponseCode("00");
				response.setResponseDesc("Transaction History SuccessFully fetch");
				response.setData(res);
			}
			else
			{
				response.setResponseCode("00");
				response.setResponseDesc("zero transaction yet..");
			
			}
			}
			
		
		
		return response;
	}
}
