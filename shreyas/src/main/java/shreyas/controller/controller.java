package shreyas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shreyas.entities.userDetails;
import shreyas.requestResponseBean.fetchTransactionRequest;
import shreyas.requestResponseBean.fundTransferRequestBean;
import shreyas.requestResponseBean.loginRequestBean;
import shreyas.requestResponseBean.registrationRequestBean;
import shreyas.requestResponseBean.responseBean;
import shreyas.serviceInterface.applicationServiceInterface;
import shreyas.serviceInterface.serviceInterface;

@RestController
@RequestMapping
public class controller 
{
	@Autowired
	serviceInterface service;
	
	@Autowired
	applicationServiceInterface appService;

	@PostMapping("/registerUser")
	public Object userRegistrationApi(@RequestBody registrationRequestBean user)
	{
		System.out.println("working....");
		
		return service.userRegistrationApi(user);
		
	}
	
	@PostMapping("/login")
	public Object userLoginApi(@RequestBody loginRequestBean user)
	{
		System.out.println("working....");
		
		return service.userLoginApi(user);
		
	}
	
	@PostMapping("/fundTransfer")
	public Object fundTransferApi(@RequestBody fundTransferRequestBean user, @RequestHeader("accessToken") String accessToken)
	{
		System.out.println("working....");
		
		
		return appService.fundTransferApi(accessToken ,user);
	}
	
	@PostMapping("/balance")
	public Object checkBalanceApi(@RequestBody fetchTransactionRequest user, @RequestHeader("accessToken") String accessToken)
	{
		System.out.println("working....");
		
		
		return appService.checkBalanceApi(accessToken ,user);
	}
	@PostMapping("/transactionHistory")
	public Object showTransactionHistoryApi(@RequestBody fetchTransactionRequest user, @RequestHeader("accessToken") String accessToken)
	{
		System.out.println("working....");
		
		
		return appService.showTransactionHistoryApi(accessToken ,user);
	}
}
