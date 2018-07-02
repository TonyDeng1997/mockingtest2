
package com.mocking.service;

import com.mocking.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
