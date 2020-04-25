package com.wallet.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.model.transactionDetails;
import com.wallet.model.userDetails;
import com.wallet.repository.UserRepository;

@Service
public class UserServiceImp implements WalletServiceInterface {
	@Autowired
	private UserRepository userRepository;

	@Override
	public userDetails createAccount(userDetails user) {
		List<userDetails> existAllAccount = findAllAccount();
		if (existAllAccount.isEmpty())
			user.setAccountId(1000000);
		else {
			int maxId = existAllAccount.stream().max(Comparator.comparingLong(userDetails::getAccountId)).get()
					.getAccountId();
			user.setAccountId(maxId + 1);
		}
		String encryptedPassword = new String(passwordEncryptor(user.getPass().getBytes()));
		user.setPass(encryptedPassword);
		String encryptedTpin = new String(passwordEncryptor(user.getPin().getBytes()));
		user.setPin(encryptedTpin);
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}
	@Override
	public userDetails updateAccount1(userDetails customer) {
		String encryptedPassword = new String(passwordEncryptor(customer.getPass().getBytes()));
		customer.setPass(encryptedPassword);
		String encryptedTpin = new String(passwordEncryptor(customer.getPin().getBytes()));
		customer.setPin(encryptedTpin);
		return userRepository.save(customer);
	}

	@Override
	public boolean deleteAccountById(int accountID) {
		userRepository.deleteById(accountID);
		return true;
	}
	@Override
	public List<userDetails> findAllAccount() {
		List<userDetails> allCustomers = userRepository.findAll();
		List<userDetails> allAccounts = allCustomers.stream().map((customer) -> {

			String decryptedPassword = new String(passwordDecryptor(customer.getPass().getBytes()));
			customer.setPass(decryptedPassword);

			
			  String decryptedTpin = new
			  String(passwordDecryptor(customer.getPin().getBytes()));
			  customer.setPin(decryptedTpin);
			 
			return customer;
		}).collect(Collectors.toList());
		return allAccounts;
		// TODO Auto-generated method stub

	}

	
	@Override
	public userDetails findAccountById(int accountId) {

		userDetails customer = userRepository.findById(accountId).get();
		String decryptedPassword = new String(passwordDecryptor(customer.getPass().getBytes()));
		customer.setPass(decryptedPassword);
		
		  String decryptedTpin = new
		  String(passwordDecryptor(customer.getPin().getBytes()));
		  customer.setPin(decryptedTpin);
		 
		return customer;

	}

	@Override
	public byte[] passwordDecryptor(byte[] password) {

		byte[] encrypted = new byte[password.length];
		for (int i = 0; i < password.length; i++) {
			encrypted[i] = (byte) ((i % 2 == 0) ? password[i] - 1 : password[i] + 1);
		}
		return encrypted;
	}

	@Override
	public byte[] passwordEncryptor(byte[] password) {
		byte[] encrypted = new byte[password.length];
		for (int i = 0; i < password.length; i++) {
			encrypted[i] = (byte) ((i % 2 == 0) ? password[i] + 1 : password[i] - 1);
		}
		return encrypted;
	}

	@Override
	public List<transactionDetails> printTransactions(int accountId) {
		// TODO Auto-generated method stub
		userDetails customer = findAccountById(accountId);
		return customer.getTransaction();

	}

	@Override
	public boolean deposit(int accountId, float money) {
		userDetails customer = findAccountById(accountId);
		customer.setBalance(customer.getBalance() + money);

		transactionDetails tData = new transactionDetails();
		tData.setAccountId(accountId);
		tData.setType("Deposit");
		tData.setAmtTransfered(money);

		java.util.Date dateJava = new java.util.Date();
		java.sql.Date date = new java.sql.Date(dateJava.getTime());
		tData.setTimeStamp(date);

		customer.getTransaction().add(tData);
		updateAccount(customer);
		return true;
	}
	@Override
	public boolean depositF(int accountId, float money,int sourceAcc) {
		userDetails customer = findAccountById(accountId);
		customer.setBalance(customer.getBalance() + money);

		transactionDetails tData = new transactionDetails();
		tData.setAccountId(accountId);
		tData.setType("fund recieved");
		tData.setSourceAcc(sourceAcc);
		tData.setAmtTransfered(money);

		java.util.Date dateJava = new java.util.Date();
		java.sql.Date date = new java.sql.Date(dateJava.getTime());
		tData.setTimeStamp(date);

		customer.getTransaction().add(tData);
		updateAccount(customer);
		return true;
	}

	
	@Override
	public userDetails updateAccount(userDetails customer) {
		String encryptedPassword = new String(passwordEncryptor(customer.getPass().getBytes()));
		customer.setPass(encryptedPassword);
		 String encryptedTpin = new
		String(passwordEncryptor(customer.getPin().getBytes()));
		 customer.setPin(encryptedTpin);
		return userRepository.save(customer);
	}
	@Override
	public boolean withdraw(int accountId, float money) {
		userDetails customer = findAccountById(accountId);
		customer.setBalance(customer.getBalance() - money);

		transactionDetails tData = new transactionDetails();
		tData.setAccountId(accountId);
		tData.setType("Withdraw");
		tData.setAmtTransfered(-money);

		java.util.Date dateJava = new java.util.Date();
		java.sql.Date date = new java.sql.Date(dateJava.getTime());
		tData.setTimeStamp(date);

		customer.getTransaction().add(tData);
		updateAccount(customer);
		return true;
	}
	@Override
	public boolean withdrawF(int accountId, float money,int senderId) {
		userDetails customer = findAccountById(accountId);
		customer.setBalance(customer.getBalance() - money);

		transactionDetails tData = new transactionDetails();
		tData.setAccountId(accountId);
		tData.setType("Fund Transfered");
		tData.setDestAcc(senderId);
		tData.setAmtTransfered(-money);

		java.util.Date dateJava = new java.util.Date();
		java.sql.Date date = new java.sql.Date(dateJava.getTime());
		tData.setTimeStamp(date);

		customer.getTransaction().add(tData);
		updateAccount(customer);
		return true;
	}


	@Override
	public boolean fundTransfer(int senderId, int receiverId, float money) {
		userDetails customer = findAccountById(senderId);
		customer.setBalance(customer.getBalance() - money);
		transactionDetails tData = new transactionDetails();
		tData.setAccountId(senderId);
		tData.setSourceAcc(senderId);
		tData.setRemAmt(customer.getBalance() - money);
		tData.setType("Fund Transfered to " + receiverId);
		tData.setDestAcc(receiverId);
		tData.setAmtTransfered(-money);
		java.util.Date dateJava = new java.util.Date();
		java.sql.Date date = new java.sql.Date(dateJava.getTime());
		tData.setTimeStamp(date);

		customer.getTransaction().add(tData);
		updateAccount(customer);
		
		return fundTransfer1( senderId, receiverId,  money);

	
	}
	@Override
	public boolean fundTransfer1(int senderId, int receiverId, float money) {
		userDetails customer = findAccountById(receiverId);
		customer.setBalance(customer.getBalance() + money);
		transactionDetails tData = new transactionDetails();
		tData.setAccountId(senderId);
		tData.setSourceAcc(senderId);
		tData.setRemAmt(customer.getBalance() + money);
		tData.setType("Fund recieved from " + senderId);
		tData.setDestAcc(receiverId);
		tData.setAmtTransfered(+money);
		java.util.Date dateJava = new java.util.Date();
		java.sql.Date date = new java.sql.Date(dateJava.getTime());
		tData.setTimeStamp(date);

		customer.getTransaction().add(tData);
		updateAccount(customer);
		return true;
	}


}
