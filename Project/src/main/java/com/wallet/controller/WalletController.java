package com.wallet.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.wallet.model.transactionDetails;
import com.wallet.model.userDetails;
import com.wallet.repository.UserRepository;
import com.wallet.repository.transactionDetailRepository;
import com.wallet.service.WalletServiceInterface;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class WalletController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private transactionDetailRepository tdRepo;
	@Autowired
	private WalletServiceInterface walletService;

	@GetMapping("/fetchUserDetail")
	public List<userDetails> getAccounts() {
		return walletService.findAllAccount();
	}

	@GetMapping("/account/{accountId}")
	public userDetails getAccount(@PathVariable int accountId) {
		
		System.out.println(walletService.findAccountById(accountId) + "hello");
		return walletService.findAccountById(accountId);

	}

	@PostMapping("/add")
	public void add(@RequestBody userDetails user) {
		walletService.createAccount(user);
	}

	@GetMapping("/account/{accountId}/printTransactions")
	public List<transactionDetails> printTransactions(@PathVariable int accountId) {
		return walletService.printTransactions(accountId);
	}

	@GetMapping("/fetchtransactionTable")
	public List<transactionDetails> getTransaction() {
		return (List<transactionDetails>) tdRepo.findAll();
	}
	@GetMapping("/exist/{id}")
	public boolean existById(@PathVariable int id)
	{
		System.out.println( userRepository.existsById(id));
		return userRepository.existsById(id);
	}

	@PutMapping("/account/{accountId}/deposit")
	public boolean deposit(@PathVariable int accountId,@RequestBody int  money) {
		//float amount = Float.parseFloat(money);
		System.out.println(userRepository.findById(accountId));
		return walletService.deposit(accountId, money);
	}
	@PutMapping("/account/{accountId}/fundTransfer2/{receiverId}")
	public boolean fundTransfer2(@PathVariable int accountId,@PathVariable int receiverId, @RequestBody float amount) {
		return walletService.fundTransfer(accountId, receiverId, amount);
	}

	@PutMapping("/account/{accountId}/withdraw")
	public boolean withdraw(@PathVariable int accountId, @RequestBody String money) {
		float amount = Float.parseFloat(money);
		return walletService.withdraw(accountId, amount);
	}
	@PutMapping("/account")
	public userDetails updateAccount(@RequestBody userDetails customer){
		return walletService.updateAccount(customer);
	}
	@DeleteMapping("/account/{accountId}")
	public boolean deleteAccount(@PathVariable int accountId){
		walletService.deleteAccountById(accountId);
		return true; 
	}
	

}