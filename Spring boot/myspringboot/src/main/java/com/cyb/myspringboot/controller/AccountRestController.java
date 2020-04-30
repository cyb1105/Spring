package com.cyb.myspringboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cyb.myspringboot.entity.Account;
import com.cyb.myspringboot.entity.User;
import com.cyb.myspringboot.exception.ResourceNotFoundException;
import com.cyb.myspringboot.repository.AccountRepository;

@RestController
public class AccountRestController {
	@Autowired
	private AccountRepository repository;
	
	@PostMapping("/accounts")
	public Account create(@RequestBody Account acct) {
		return repository.save(acct);
	}
	
	@GetMapping("/accounts")
	public List<Account> getAccounts(){
		return repository.findAll();
	}
	
	@GetMapping("/accounts/{id}")
	public Account getAcct(@PathVariable Long id) {
		Optional<Account> acctOpt = repository.findById(id);
		Account acct = acctOpt.orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));
		return acct;
	}
	
	
	@PutMapping("/accounts/{id}")
	public Account updateAcct(@PathVariable String username,@RequestBody Account acctDetail) {
		Account acct = repository.findByUsername(username).orElseThrow(()-> new ResourceNotFoundException("Account","username",username));
		acct.setUsername(acctDetail.getUsername());
		acct.setPassword(acctDetail.getPassword());
		Account updatedAccount = repository.save(acct);
		return updatedAccount;
	}
	
	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<String> deleteAcct(@PathVariable Long id){
		Account acct= repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Account", "Id", id));
		repository.delete(acct);
		return new ResponseEntity<String>(acct.getId()+"deleted..",HttpStatus.OK);
	}
	
	
	
	
	
	
	
	

}
