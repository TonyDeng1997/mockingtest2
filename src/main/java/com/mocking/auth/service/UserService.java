package com.mocking.auth.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.mocking.auth.model.User;

public interface UserService {
	void save(User user);

	User findByUsername(String username);

	String findHomePathByUsername(String username);

	/*
	 * @return String User's home path consists of login name with a dash and a
	 * generated uuid.
	 */
	String generateUserHomePath(String userLoginName);

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
