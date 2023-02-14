package com.system.user.usersystem.services.impl;

import com.system.user.usersystem.models.User;
import com.system.user.usersystem.repository.UserRepository;
import com.system.user.usersystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    //Metodo para crear usuario con validaciones básicas
    @Override
    public ResponseEntity<User> saveUser(User user){

       User userLocal = userRepository.findOneByEmail(user.getEmail());
       if(userLocal != null){
           System.out.println("El usuario ya existe");
           return ResponseEntity.badRequest().build();

       }else {

            user.setPassword(encoder.encode(user.getPassword()));
            userLocal = userRepository.save( user );
            userLocal.setPassword("");
       }
       return ResponseEntity.ok(userLocal);
    }

    //Obtenemos usuario por email
    @Override
    public ResponseEntity<User> getUser(String email) {
      User userLocal = userRepository.findOneByEmail( email );

      if( userLocal == null ) {
          return ResponseEntity.badRequest().build();
      }

        return ResponseEntity.ok( userLocal );
    }

    //Borramos usuario por id
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
    //Buscamos usuario por id
    @Override
    public ResponseEntity<User> findById(Long id) {
        Optional<User> userLocal = userRepository.findById( id);
        if( userLocal.isPresent()){
            return ResponseEntity.ok( userLocal.get() );
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
    //Actualizamos usuario
    @Override
    public ResponseEntity<User> updateUser(User user) {

        if( user.getId() == null){
            System.out.println("No existe el usuario con id " +user.getId());
            return ResponseEntity.badRequest().build();
        }

        if (!userRepository.existsById( user.getId())) {
            System.out.println("No existe usuario con id " + user.getId());
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(encoder.encode(user.getPassword()));
        User result = userRepository.save( user);
        result.setPassword("");
        return ResponseEntity.ok(result);
    }


    //Listamos todos los usuarios
    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
