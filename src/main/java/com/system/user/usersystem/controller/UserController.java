package com.system.user.usersystem.controller;

import com.system.user.usersystem.models.User;
import com.system.user.usersystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> all() {
        return ResponseEntity.ok(userService.allUsers());

    }
    @GetMapping("/find/{email}")
    public ResponseEntity<User> findEmail( @PathVariable("email") String email  ){
        return userService.getUser(email);

    }
    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user ) {

        return userService.saveUser(user);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> delete( @PathVariable Long id)  {
        return userService.deleteUser(id);

    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<User> findbyid(@PathVariable Long id)  {
        return userService.findById( id );

    }
    @PostMapping ("/update")
    public ResponseEntity<User> update(@RequestBody User user ) {

        return userService.updateUser(user);
    }

}
