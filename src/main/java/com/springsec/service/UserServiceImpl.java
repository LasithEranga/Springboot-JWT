package com.springsec.service;

import com.springsec.model.Role;
import com.springsec.model.User;
import com.springsec.repo.RolesRepo;
import com.springsec.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements  UserService{

    private final UserRepo userRepo;
    private final RolesRepo rolesRepo;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {}", user.getName());
        return userRepo.save(user);

    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {}", role.getName());

        return rolesRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepo.findByUsername(username);
        Role role = rolesRepo.findByName(roleName);
        log.info("Adding role {} to {}", user.getName(), role.getName());
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetaching user {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetaching all users");
        return userRepo.findAll();
    }
}
