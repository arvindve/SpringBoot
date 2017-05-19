package com.ai.springboot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ai.springboot.model.SiteUser;

@Repository
public interface UserDao extends CrudRepository<SiteUser, Long> {
	
	SiteUser findByEmail(String email);

}
