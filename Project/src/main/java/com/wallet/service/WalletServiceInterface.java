package com.wallet.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.wallet.model.transactionDetails;
import com.wallet.model.userDetails;
@Service
public interface WalletServiceInterface {
	userDetails createAccount(userDetails user);
	List<userDetails> findAllAccount();
	userDetails findAccountById(int accountId);
	List<transactionDetails> printTransactions(int accountId);
	boolean fundTransfer(int senderId, int receiverId, float money);
	userDetails updateAccount(userDetails customer);
	boolean withdraw(int accountId, float money);
	boolean deposit(int accountId, float money);
	boolean deleteAccountById(int accountID);
	boolean fundTransfer1(int senderId, int receiverId, float money);

}
