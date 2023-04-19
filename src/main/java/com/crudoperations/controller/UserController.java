package com.crudoperations.controller;

import com.crudoperations.entity.User;
import com.crudoperations.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public User addUser(@RequestBody User user){
        return userRepo.save(user);
    }

    @GetMapping
    public List<User> getAllTheUsers(){
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()){
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()){
            User existingUser = optionalUser.get();
            existingUser.setName(user.getName());
            existingUser.setBalance(user.getBalance());
            userRepo.save(existingUser);
            return ResponseEntity.ok(existingUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()){
            userRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
