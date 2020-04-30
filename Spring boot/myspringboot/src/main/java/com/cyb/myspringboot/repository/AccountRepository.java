package com.cyb.myspringboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyb.myspringboot.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	Optional<Account> findByUsername(String username);
	
}
