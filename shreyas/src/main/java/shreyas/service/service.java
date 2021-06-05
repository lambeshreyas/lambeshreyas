package shreyas.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shreyas.daoInterface.daoInterface;
import shreyas.entities.accountDetails;
import shreyas.entities.userDeatilsId;
import shreyas.entities.userDetails;
import shreyas.requestResponseBean.fundTransferRequestBean;
import shreyas.requestResponseBean.loginRequestBean;
import shreyas.requestResponseBean.registrationRequestBean;
import shreyas.requestResponseBean.responseBean;
import shreyas.serviceInterface.serviceInterface;

@Service
@Transactional
public class service implements  serviceInterface{
	
@Autowired
daoInterface dao;

	@Override
	public Object userRegistrationApi(registrationRequestBean user) {
		// TODO Auto-generated method stub
		responseBean response = new responseBean();
		try {
			
			if(user.getAccount_no() != 0 && !(user.getUser_id().isBlank()) && !(user.getPassword().isBlank()) )
			{
		
		userDetails userDetails =  new userDetails();
		userDetails.setPassword(user.getPassword());
		userDeatilsId userDetailsId =  new userDeatilsId();
		userDetailsId.setAccount_no(user.getAccount_no());
		userDetailsId.setUser_id(user.getUser_id());
		userDetails.setUserDetailid(userDetailsId);
		
		 response = (responseBean) dao.addUserDetails(userDetails);
		 if(response.getResponseCode() == "00")
		 {
			 accountDetails account =  new accountDetails();
			 account.setAccount_no(user.getAccount_no());
			 account.setAvailable_balance(user.getAvailable_balance());
			  response = (responseBean) dao.addAcoountDeatils(account);
			  return response;
		 }
		 	}
			else
			{
				response.setResponseCode("401");
				response.setResponseDesc("Invalid Input");
			}
		}
		catch(Exception e)
		{
			response.setResponseCode("501");
			response.setResponseDesc("Something went wrong");
		}
		 return response;
		
	}

	@Override
	public Object userLoginApi(loginRequestBean user) {
		responseBean response = new responseBean();
		response.setResponseCode("401");
		response.setResponseDesc("Invalid Input");
		
		// TODO Auto-generated method stub
		if(!(user.getPassword().isBlank()) && !(user.getPassword().isBlank()) )
				{
			          return  dao.userLoginApi(user);
				}
		return response;
	}



}
