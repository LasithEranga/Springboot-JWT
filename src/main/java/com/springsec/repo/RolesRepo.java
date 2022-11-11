package com.springsec.repo;

import com.springsec.model.Role;
import com.springsec.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
