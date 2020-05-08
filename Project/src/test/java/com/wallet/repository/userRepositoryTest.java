package com.wallet.repository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.wallet.model.userDetails;
@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class userRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private UserRepository userRepository;
	private userDetails getUser() {
		userDetails user = new userDetails();
		user.setAccNumber(72331123);
		user.setAccountId(1000010);
		user.setBalance(1000);
		user.seteMail("ram@gmail.com");
		user.setFullName("Ram Singh");
		user.setPass("ram123");
		user.setPin("ram123");
		user.setpNumber(755012548);
		user.setTransaction(null);
		user.setUserName("Ram123");
		return user;
	}

	@Test
	public void testFindByIdCustomer() {
		userDetails savedCustomer = entityManager.persist(getUser());

		userDetails entityManagerCustomer = entityManager.find(userDetails.class, savedCustomer.getAccountId());
		userDetails retrivedCustomer = userRepository.findById(savedCustomer.getAccountId()).get();

		assertThat(retrivedCustomer).isEqualToComparingFieldByField(entityManagerCustomer);
	}

	@Test
	public void deleteCustomerById() {
		userDetails savedCustomer = entityManager.persist(getUser());
		userRepository.deleteById(savedCustomer.getAccountId());
		assertNull(entityManager.find(userDetails.class, savedCustomer.getAccountId()));
	}

	@Test
	public void testSaveUser() {
		userDetails user = getUser();
		userDetails saveIntoDB = userRepository.save(user);
		userDetails getFromDB = entityManager.find(userDetails.class, saveIntoDB.getAccountId());
		assertThat(getFromDB).isEqualToComparingFieldByField(saveIntoDB);

	}
	@Test
	public void updateCustomer() {
		userDetails savedCustomer = entityManager.persist(getUser());
		savedCustomer.setFullName("Puneet Sharma");
		userDetails updatedCustomer = userRepository.save(savedCustomer);
		assertThat(updatedCustomer.getFullName()).isEqualTo("Puneet Sharma");
	}
	public void findallCustomers() {
		assertNotNull(userRepository.findAll());
	}

}
