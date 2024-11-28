package com.offliner.offliner.controller;

import com.offliner.offliner.exception.UserNotFaundException;
import com.offliner.offliner.exception.UserNotFaundUsernameException;
import com.offliner.offliner.model.User;
import com.offliner.offliner.repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepos userRepos;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {

        return userRepos.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){

        return userRepos.findAll();
    }

    // Получаем данные пользователя по имени
    @GetMapping("users/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userRepos.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepos.findById(id)
                .orElseThrow(()->new UserNotFaundException(id));
    }

    @PutMapping("/user/profile")
    public User updateUserProfile(@RequestBody User newUser, @RequestParam String username) {
        return userRepos.findByUsername(username)
                .map(user -> {
                    // Обновляем только те поля, которые может редактировать сам пользователь
                    user.setFirst_name(newUser.getFirst_name());
                    user.setSecond_name(newUser.getSecond_name());
                    user.setEmail(newUser.getEmail());
                    user.setPhone(newUser.getPhone());
                    // Роль пользователя не меняется (пользователь не может менять свою роль)
                    return userRepos.save(user);
                })
                .orElseThrow(() -> new UserNotFaundUsernameException(username));
    }


    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepos.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setFirst_name(newUser.getFirst_name());
                    user.setSecond_name(newUser.getSecond_name());
                    user.setEmail(newUser.getEmail());
                    user.setPhone(newUser.getPhone());
                    user.setRole(newUser.getRole());
                    return userRepos.save(user);
                }).orElseThrow(()-> new UserNotFaundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepos.existsById(id)){
            throw new UserNotFaundException(id);
        }

        userRepos.deleteById(id);
        return "User with id " + id + " has been deleted success.";

    }

}




