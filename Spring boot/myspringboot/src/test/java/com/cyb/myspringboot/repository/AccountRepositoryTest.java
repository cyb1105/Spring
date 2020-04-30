package com.cyb.myspringboot.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cyb.myspringboot.entity.Account;
import com.jayway.jsonpath.Option;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {
	@Autowired
	AccountRepository repository;
	@Test
	public void findByUsername() throws Exception{
//		Account existAcct = repository.findByUsername("spring");
//		assertThat(existAcct).isNotNull();
//		
//		Account notExistAcct = repository.findByUsername("test");
//		assertThat(notExistAcct).isNull();
		
		Optional<Account> existOpt = repository.findByUsername("spring");
		System.out.println(existOpt.isPresent());//true
		if(existOpt.isPresent()) {
			Account existAcct = existOpt.get();
			System.out.println(existAcct);
		}
		Account account = existOpt.orElseThrow(()->new RuntimeException("존재하지 않는 username입니다."));
		System.out.println("존재하는 Account" + account);
		
		Optional<Account> notExistOpt = repository.findByUsername("test");
		System.out.println(notExistOpt.isPresent());//false
		//orElseThrow()의 아규먼트 타입이? Supplier
		//Supplier의 추상메서드 - T get()
		Account notExistAcct = notExistOpt.orElseThrow(()->new RuntimeException("존재하지 않는 username입니다."));
		System.out.println(notExistAcct);
		//NoSuchElementException발생
//		Account notexistAcct = notexistOpt.get();
//		System.out.println(notexistAcct);		
	}
	
	@Test @Ignore
	public void account() throws Exception{
		Account account = new Account();
		account.setUsername("spring");
		account.setPassword("1234");
		
		Account saveAcct = repository.save(account);
		System.out.println(saveAcct);
		assertThat(saveAcct).isNotNull();
	}

}
