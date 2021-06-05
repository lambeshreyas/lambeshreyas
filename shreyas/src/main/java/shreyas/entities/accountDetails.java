package shreyas.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account_details")
public class accountDetails 
{
	 @Id
	 @Column(name ="account_no")
     private long account_no;
	
	@Column(name = "available_balance", columnDefinition="double default '0.00'")
   private double  available_balance;
	
	@OneToMany(mappedBy = "account_no")
    List<transactionDetails> transaction;

	public long getAccount_no() {
		return account_no;
	}

	public void setAccount_no(long account_no) {
		this.account_no = account_no;
	}

	public double getAvailable_balance() {
		return available_balance;
	}

	public void setAvailable_balance(double available_balance) {
		this.available_balance = available_balance;
	}

	public List<transactionDetails> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<transactionDetails> transaction) {
		this.transaction = transaction;
	}
	
	
	
}
