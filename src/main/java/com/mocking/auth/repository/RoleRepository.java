package com.mocking.auth.repository;

import com.mocking.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
/*@author feifei*/

public interface RoleRepository extends JpaRepository<Role, Long>{
}
