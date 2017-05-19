package com.ai.springboot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ai.springboot.model.VerificationToken;

@Repository
public interface VerificationDao extends CrudRepository<VerificationToken, Long> {
	
	VerificationToken findByToken(String token);

}
