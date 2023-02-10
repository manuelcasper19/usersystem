package com.system.user.usersystem.services.impl;

import com.system.user.usersystem.models.User;
import com.system.user.usersystem.repository.UserRepository;
import com.system.user.usersystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<User> saveUser(User user){

       User userLocal = userRepository.findOneByEmail(user.getEmail());
       if(userLocal != null){
           System.out.println("El usuario ya existe");
           return ResponseEntity.badRequest().build();

       }else {
          // PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
           System.out.println("Password " + user.getPassword());
          // user.setPassword(encoder.encode(user.getPassword()));
            userLocal = userRepository.save( user );
       }
       return ResponseEntity.ok(userLocal);
    }

    @Override
    public ResponseEntity<User> getUser(String email) {
      User userLocal = userRepository.findOneByEmail( email );
      if( userLocal == null ) {
          return ResponseEntity.badRequest().build();
      }
        return ResponseEntity.ok( userLocal );
    }

    @Override
    public ResponseEntity<User> deleteUser(Long id) {
        Optional<User> userLocal = userRepository.findById( id);
        if( userLocal.isPresent()){
            userRepository.deleteById( id );
            return ResponseEntity.ok( userLocal.get() );
        }else{
            return ResponseEntity.badRequest().build();
        }

    }

    @Override
    public ResponseEntity<User> findById(Long id) {
        Optional<User> userLocal = userRepository.findById( id);
        if( userLocal.isPresent()){
            return ResponseEntity.ok( userLocal.get() );
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<User> updateUser(User user) {

        if( user.getId() == null){
            System.out.println("No existe el libro cn id " +user.getId());
            return ResponseEntity.badRequest().build();
        }

        if (!userRepository.existsById( user.getId())) {
            System.out.println("No existe usuario con id " + user.getId());
            return ResponseEntity.badRequest().build();
        }
        User result = userRepository.save( user);
        return ResponseEntity.ok(result);
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
