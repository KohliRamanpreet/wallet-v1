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

		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public List<userDetails> findAllAccount() {
		List<userDetails> allCustomers = userRepository.findAll();
		List<userDetails> allAccounts = allCustomers.stream().map((customer) -> {

			String decryptedPassword = new String(passwordDecryptor(customer.getPass().getBytes()));
			customer.setPass(decryptedPassword);

			/*
			 * String decryptedTpin = new
			 * String(passwordDecryptor(customer.getTransactionPin().getBytes()));
			 * customer.setTransactionPin(decryptedTpin);
			 */
			return customer;
		}).collect(Collectors.toList());
		return allAccounts;
		// TODO Auto-generated method stub

	}

	/*
	 * @Override public byte[] passwordEncryptor(byte[] password) { byte[] encrypted
	 * = new byte[password.length]; for (int i = 0; i < password.length; i++) {
	 * encrypted[i] = (byte)((i%2 == 0) ? password[i] + 1 : password[i] - 1); }
	 * return encrypted; }
	 * 
	 * @Override public byte[] passwordDecryptor(byte[] password) { byte[] encrypted
	 * = new byte[password.length]; for (int i = 0; i < password.length; i++) {
	 * encrypted[i] = (byte)((i%2 == 0) ? password[i] - 1 : password[i] + 1); }
	 * return encrypted; }
	 */
	@Override
	public userDetails findAccountById(int accountId) {

		userDetails customer = userRepository.findById(accountId).get();
		String decryptedPassword = new String(passwordDecryptor(customer.getPass().getBytes()));
		customer.setPass(decryptedPassword);
		/*
		 * String decryptedTpin = new
		 * String(passwordDecryptor(customer.getTransactionPin().getBytes()));
		 * customer.setTransactionPin(decryptedTpin);
		 */
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
	public userDetails updateAccount(userDetails customer) {
		String encryptedPassword = new String(passwordEncryptor(customer.getPass().getBytes()));
		customer.setPass(encryptedPassword);
		// String encryptedTpin = new
		// String(passwordEncryptor(customer.getTransactionPin().getBytes()));
		// customer.setTransactionPin(encryptedTpin);
		return userRepository.save(customer);
	}

	@Override
	public boolean fundTransfer(int senderId, int receiverId, float money) {
		userDetails customer = findAccountById(senderId);
		customer.setBalance(customer.getBalance() - money);

		transactionDetails tData = new transactionDetails();
		tData.setSourceAcc(senderId);
		tData.setType("Fund Transfered to " + receiverId);
		tData.setDestAcc(receiverId);
		tData.setAmtTransfered(-money);

		java.util.Date dateJava = new java.util.Date();
		java.sql.Date date = new java.sql.Date(dateJava.getTime());
		tData.setTimeStamp(date);

		customer.getTransaction().add(tData);
		updateAccount(customer);

		customer = findAccountById(receiverId);
		customer.setBalance(customer.getBalance() + money);
		tData.setType("Fund Transfered from " + senderId);
		tData.setAmtTransfered(money);
		customer.getTransaction().add(tData);
		updateAccount(customer);
		return true;
	}

}
