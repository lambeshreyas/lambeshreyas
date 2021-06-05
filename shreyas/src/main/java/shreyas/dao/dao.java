package shreyas.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shreyas.daoInterface.daoInterface;
import shreyas.entities.accountDetails;
import shreyas.entities.transactionDetails;
import shreyas.entities.userDeatilsId;
import shreyas.entities.userDetails;
import shreyas.requestResponseBean.fundTransferRequestBean;
import shreyas.requestResponseBean.loginRequestBean;
import shreyas.requestResponseBean.registrationRequestBean;
import shreyas.requestResponseBean.responseBean;
import shreyas.utils.jwtClass;

@Repository
@Transactional
public class dao implements daoInterface{
	
	
	
	@Autowired
	private  SessionFactory sessionFactory;
	
	protected Session getSession(){
	     
		 return	sessionFactory.getCurrentSession();
     		   
 }
	@Override
	public Object addUserDetails(userDetails user) {
		// TODO Auto-generated method stub
		responseBean response = new responseBean();
		
		
		try {
		Session session = getSession();
		Criteria cr = session.createCriteria(userDetails.class);
		cr.add(Restrictions.eq("userDetailid.account_no", user.getUserDetailid().getAccount_no()));
		
		Criteria cr2 = session.createCriteria(userDetails.class);
		cr2.add(Restrictions.eq("userDetailid.user_id", user.getUserDetailid().getUser_id()));
	
		
		
	
		if (cr.list().isEmpty() && cr2.list().isEmpty()) {
		session.save(user);
		response.setResponseCode("00");
		response.setResponseDesc("User has been Register");
		}
		else 
		{
			response.setResponseCode("401");
			response.setResponseDesc("userAlredyExist");
		}
		}
		catch(Exception e)
		{
			response.setResponseCode("501");
			response.setResponseDesc("something went wrong");
		}
		return response;
		
	}
	@Override
	public Object addAcoountDeatils(accountDetails user) {
		// TODO Auto-generated method stub
		
       responseBean response = new responseBean();
		
		try {
		Session session = getSession();
		Criteria cr = session.createCriteria(accountDetails.class);
		cr.add(Restrictions.eq("account_no", user.getAccount_no()));
		
		if (cr.list().isEmpty()) {
		session.save(user);
		response.setResponseCode("00");
		response.setResponseDesc("User has been Register");
		}
		else 
		{
			response.setResponseCode("401");
			response.setResponseDesc("userAlredyExist");
		}
		}
		catch(Exception e)
		{
			response.setResponseCode("501");
			response.setResponseDesc("something went wrong");
		}
		return response;
	}
	
	@Override
	public Object userLoginApi(loginRequestBean user) {
responseBean response = new responseBean();
		
		
		try {
		Session session = getSession();
		Criteria cr = session.createCriteria(userDetails.class);
		Criterion accNo = Restrictions.eq("password", user.getPassword());
		Criterion UserId = Restrictions.eq("userDetailid.user_id", user.getUserId());
		
		LogicalExpression andExp = Restrictions.and(accNo, UserId);
		cr.add(andExp);
		if (cr.list().isEmpty()) {
			response.setResponseCode("401");
			response.setResponseDesc("Login faild...Invalid Input");
		
		}
		else 
		{
			response.setResponseCode("00");
			response.setResponseDesc("Sucessfully login");
			jwtClass jwt = new jwtClass();
			response.setData(jwt.createJWT(user.getPassword(), user.getUserId()));
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
	public Object fundTransfer(transactionDetails transaction) {
		// TODO Auto-generated method stub
		Session session = getSession();
		
		session.save(transaction);
		
		return true;
	}
	
	@Override
	public Object getAccountByNumberApi(long accountNumber) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Criteria cr1 = session.createCriteria(accountDetails.class);
		cr1.add(Restrictions.eq("account_no", accountNumber));
		
		List<accountDetails> accountCredit = cr1.list();
		return accountCredit;
	}
	
	public Object UpdateaccountBalance(double balnce, long accountNumber)
	{
    
        Session session = getSession();
		Criteria cr1 = session.createCriteria(accountDetails.class);
		cr1.add(Restrictions.eq("account_no", accountNumber));
		
		      if(!(cr1.list().isEmpty()))
				{
		    	  accountDetails account = new accountDetails();
		    	  account = (accountDetails) cr1.list().get(0);
		    	  account.setAvailable_balance(balnce);
		  		
				}
		
		return true;
	}
	

	
	
	
	
	
}
