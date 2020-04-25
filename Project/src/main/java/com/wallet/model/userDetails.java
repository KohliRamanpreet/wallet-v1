package com.wallet.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name="details_wallets")
public class userDetails {
	
	@Id
	@Column(name="Account_Id",length=7)
	private int accountId;
	@Column(name = "userName")
	private String userName;
	@Column(name = "fullName")
	private String fullName;
	@Column(name = "pass")
	private String pass;
	@Column(name = "accNumber")
	private String accountNo;
	@Email
	@Column(name = "eMail")
	private String eMail;
	@Column(name = "pNumber")
	private long pNumber;
	@Column(name="Pin")
	private String pin;
	
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}

	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,targetEntity = transactionDetails.class)
	@JoinColumn(name="account_Id",referencedColumnName = "account_id")
	private List<transactionDetails> transaction=new  ArrayList<>();
	@Column(name="balance")
	private float balance;
	
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public int getAccountId() {
		return accountId;
	}
	public int setAccountId(int accountId) {
		return this.accountId = accountId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public long getpNumber() {
		return pNumber;
	}
	public void setpNumber(long pNumber) {
		this.pNumber = pNumber;
	}
	public List<transactionDetails> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<transactionDetails> transaction) {
		this.transaction = transaction;
	}
	
	@Override
	public String toString() {
		return "userDetails [userName=" + userName + ", fullName=" + fullName + ", pass=" + pass + ", accountNo="
				+ accountNo + ", eMail=" + eMail + ", pNumber=" + pNumber + ", transaction=" + transaction + "]";
	}
	
}