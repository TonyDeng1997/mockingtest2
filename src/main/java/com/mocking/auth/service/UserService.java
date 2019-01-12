package com.mocking.auth.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.mocking.auth.model.User;

public interface UserService {
	void save(User user);

	User findByUsername(String username);

	String findHomePathByUsername(String username);

	/*
	 * @return String return login principal's name
	 */
	String getUserLoginName();

	/*
	 * @return String User's home path consists of login name with a dash and a
	 * generated uuid.
	 */
	String generateUserHomePath(String userLoginName);

	default Object getUserFromLogin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getPrincipal();
	}

	default String createWorkingDirectory(String path) {
		Path pathObject = Paths.get(path);
		if (!Files.exists(pathObject)) {
			try {
				Files.createDirectories(pathObject);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}
		return path;
	}
}
