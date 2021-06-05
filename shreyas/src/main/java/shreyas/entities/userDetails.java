package shreyas.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_details")
public class userDetails implements Serializable  
{

	@EmbeddedId
	userDeatilsId userDetailid;
	
 
    @Column(name="pass")
    private String password;
 

public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public userDeatilsId getUserDetailid() {
	return userDetailid;
}
public void setUserDetailid(userDeatilsId userDetailid) {
	this.userDetailid = userDetailid;
}
 
 
}
