package com.prakhar.blogapp;

import com.prakhar.blogapp.models.Role;
import com.prakhar.blogapp.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner{
	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		Role role1 = new Role(1, "Member");
		Role role2 = new Role(2, "Admin");
		Role role3 = new Role(3, "Owner");
		List<Role> roles = List.of(role1, role2, role3);
		List<Role> savedRoles = roleRepository.saveAll(roles);
		savedRoles.forEach(role -> System.out.println(role.getName()));
	}
}
