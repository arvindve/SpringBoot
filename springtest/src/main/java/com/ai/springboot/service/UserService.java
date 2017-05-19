package com.ai.springboot.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ai.springboot.dao.UserDao;
import com.ai.springboot.dao.VerificationDao;
import com.ai.springboot.model.SiteUser;
import com.ai.springboot.model.TokenType;
import com.ai.springboot.model.VerificationToken;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private VerificationDao verificationDao;

	public void register(SiteUser user) {
		user.setRole("ROLE_USER");
		userDao.save(user);
	}

	public void save(SiteUser user) {
		userDao.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		SiteUser user = userDao.findByEmail(email);
		if (user == null) {
			return null;
		}

		List<GrantedAuthority> list = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());

		String password = user.getPassword();
		Boolean enabled = user.getEnabled();
		return new User(email, password, enabled, true, true, true, list);

	}

	public String createEmailVerificationToken(SiteUser user) {
		VerificationToken token = new VerificationToken(UUID.randomUUID().toString(), user, TokenType.REGISTRATION);
		verificationDao.save(token);
		return token.getToken();

	}

	public VerificationToken getVerificationToken(String token) {
		return verificationDao.findByToken(token);
	}

	public SiteUser getUser(String email) {

		return userDao.findByEmail(email);

	}

}
