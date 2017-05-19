package com.ai.springboot.dao;

import org.springframework.data.repository.CrudRepository;

import com.ai.springboot.model.Profile;
import com.ai.springboot.model.SiteUser;

public interface ProfileDao extends CrudRepository<Profile, Long> {
	
	Profile findByUser(SiteUser user);

}
