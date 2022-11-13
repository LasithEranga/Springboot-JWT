package com.springsec;

import com.springsec.model.Role;
import com.springsec.model.User;
import com.springsec.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringsecApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPERADMIN"));

            userService.saveUser(new User(null, "Lasith", "lasith", "123456", new ArrayList<>()));
            userService.saveUser(new User(null, "Eranda", "eranda", "123456", new ArrayList<>()));
            userService.saveUser(new User(null, "Handapangoda", "handapangoda", "123456", new ArrayList<>()));

            userService.addRoleToUser("lasith", "ROLE_USER");
            userService.addRoleToUser("lasith", "ROLE_ADMIN");

            userService.addRoleToUser("eranda", "ROLE_USER");

        };
    }

}
