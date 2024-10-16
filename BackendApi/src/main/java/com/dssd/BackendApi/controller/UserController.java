package com.dssd.BackendApi.controller;

import com.dssd.BackendApi.model.Users;
import com.dssd.BackendApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Users user) {
        user.setPassword(bcrypt.encode(user.getPassword()));
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping
    public ResponseEntity<Iterable<Users>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

}
