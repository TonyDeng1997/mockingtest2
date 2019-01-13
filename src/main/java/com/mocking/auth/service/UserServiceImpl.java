package com.mocking.auth.service;

import java.io.File;
import java.util.HashSet;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.mocking.auth.repository.RoleRepository;
import com.mocking.auth.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	SecurityService securityService;
	
	@Override
	public void save(com.mocking.auth.model.User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		
		String loginName = user.getUsername();
		String homePath = createWorkingDirectory(generateUserHomePath(loginName));
		user.setHomePath(homePath);
		userRepository.save(user);
	}
	
	@Override
	public com.mocking.auth.model.User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public String findHomePathByUsername(String username) {
		com.mocking.auth.model.User user = findByUsername(username);
		return user.getHomePath();
	}

	@Override
	public String generateUserHomePath(String userLoginName) {
		return "/tmp/"+  userLoginName + "-" + UUID.randomUUID().toString();
	}	
}
