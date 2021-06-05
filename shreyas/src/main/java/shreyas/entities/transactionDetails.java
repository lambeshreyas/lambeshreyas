package shreyas.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.ws.rs.DefaultValue;
@Entity
@Table(name = "transaction_details")
public class transactionDetails implements Serializable
{ 
	
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int TransactionId;
     
     @ManyToOne
     @JoinColumn
     private accountDetails account_no;
	
     @Column(name = "transaction_flag", nullable = false)
	 private String transaction_flag;
	
     
     @Column(name = "transaction_amount", nullable = false)
	private double transaction_amount;
	
     
     @Column(name = "referance_number", nullable = false)
	 private long referance_number;
	
	
	
	public accountDetails getAccount_no() {
		return account_no;
	}
	public void setAccount_no(accountDetails account_no) {
		this.account_no = account_no;
	}
	public String getTransaction_flag() {
		return transaction_flag;
	}
	public void setTransaction_flag(String transaction_flag) {
		this.transaction_flag = transaction_flag;
	}
	public double getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(double transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public long getReferance_number() {
		return referance_number;
	}
	public void setReferance_number(long referance_number) {
		this.referance_number = referance_number;
	}
	public int getTransactionId() {
		return TransactionId;
	}
	public void setTransactionId(int transactionId) {
		TransactionId = transactionId;
	}
	
	
}
