package shreyas.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class userDeatilsId implements Serializable 
{
	@Column(name = "user_id", nullable = false)
    private String user_id;
    
    
	@Column(name = "account_no", nullable = false)
    private long account_no;


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public long getAccount_no() {
		return account_no;
	}


	public void setAccount_no(long account_no) {
		this.account_no = account_no;
	}
	
	
}
