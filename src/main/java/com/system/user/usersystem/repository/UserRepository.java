package com.system.user.usersystem.repository;

import com.system.user.usersystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByEmail(String email);
}