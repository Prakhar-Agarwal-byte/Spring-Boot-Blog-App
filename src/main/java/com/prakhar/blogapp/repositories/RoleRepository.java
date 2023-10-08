package com.prakhar.blogapp.repositories;

import com.prakhar.blogapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
