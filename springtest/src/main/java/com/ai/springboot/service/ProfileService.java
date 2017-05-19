package com.ai.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.ai.springboot.dao.ProfileDao;
import com.ai.springboot.model.Profile;
import com.ai.springboot.model.SiteUser;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileDao profileDao;

	@PreAuthorize("isAuthenticated()")
	public void save(Profile profile) {

		profileDao.save(profile);
	}
	
	@PreAuthorize("isAuthenticated()")
	public Profile getProfile(SiteUser user) {

		return profileDao.findByUser(user);

	}

}
