package com.system.user.usersystem.services;

import com.system.user.usersystem.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

//Inteface para las implementaciones
public interface UserService {
    ResponseEntity<User> saveUser(User user);
    ResponseEntity<User>getUser(String email);
    ResponseEntity<User> deleteUser( Long id );
    ResponseEntity<User> findById( Long id );
    ResponseEntity<User> updateUser(User user);
    List<User> allUsers();

}
