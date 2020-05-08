package com.wallet.model;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_Wallets")
public class transactionDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="transaction_id")
	private int transactionId;
	@Column(name="account_id")
	private long accountId;
	@Column(name="sourceAcc")
	private long sourceAcc;
	@Column(name="destAcc")
	private long destAcc;
	@Column(name="transType")
	private String type;
	@Column(name="remAmt")
	public float remAmt;
	@Column(name="timeStamp")
	private Date timeStamp;
	@Column(name="amtTransfered")
	private double amtTransfered;
public int getTransactionId() {
		return transactionId;
	}

public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	//	public userDetails getUser() {
//		return user;
//	}
//	public void setUser(userDetails user) {
//		this.user = user;
//	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
//	
	public long getSourceAcc() {
		return sourceAcc;
	}
	public void setSourceAcc(long sourceAcc) {
		this.sourceAcc = sourceAcc;
	}
	public long getDestAcc() {
		return destAcc;
	}
	public void setDestAcc(long destAcc) {
		this.destAcc = destAcc;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public float getRemAmt() {
		return remAmt;
	}

	public void setRemAmt(float remAmt) {
		this.remAmt = remAmt;
	}
	
	public double getAmtTransfered() {
		return amtTransfered;
	}
	public void setAmtTransfered(double amtTransfered) {
		this.amtTransfered = amtTransfered;
	}
	@Override
	public String toString() {
		return "transactionDetails [id=" + transactionId + ", sourceAcc=" + sourceAcc + ", destAcc=" + destAcc + ", type=" + type
				+ ", timeStamp=" + timeStamp + ", amtTransfered=" + amtTransfered + "]";
	}
	
	


}
