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
		return userRepository.save(user);
	}
<<<<<<< HEAD
=======
	@Override
	public userDetails updateAccount1(userDetails customer) {
		return userRepository.save(customer);
	}
>>>>>>> b75efe7769d77e5fa787dc03c5ca46048bc1602a

	@Override
	public boolean deleteAccountById(int accountID) {
		userRepository.deleteById(accountID);
		return true;
	}
	@Override
	public List<userDetails> findAllAccount() {
		List<userDetails> allCustomers = userRepository.findAll();
<<<<<<< HEAD
		List<userDetails> allAccounts = allCustomers.stream().map((customer) -> {
=======
		List<userDetails> allAccounts = allCustomers.stream().map((customer) -> { 
>>>>>>> b75efe7769d77e5fa787dc03c5ca46048bc1602a
			return customer;
		}).collect(Collectors.toList());
		return allAccounts;

	}
	@Override
	public userDetails findAccountById(int accountId) {

		userDetails customer = userRepository.findById(accountId).get();
		return customer;

	}

<<<<<<< HEAD

=======
>>>>>>> b75efe7769d77e5fa787dc03c5ca46048bc1602a

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
	public userDetails updateAccount(userDetails customer) {
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
<<<<<<< HEAD
	
=======

>>>>>>> b75efe7769d77e5fa787dc03c5ca46048bc1602a

}
