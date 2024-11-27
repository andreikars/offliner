package com.offliner.offliner.controller;

import com.offliner.offliner.model.LoginRequest;
import com.offliner.offliner.model.User;
import com.offliner.offliner.repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepos userRepos;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User newUser) {
        Map<String, String> errors = new HashMap<>();

        // Проверка на уникальность username
        if (userRepos.existsByUsername(newUser.getUsername())) {
            errors.put("username", "Username is already taken");
        }

        newUser.setRole("USER"); // Устанавливаем роль USER

        // Проверка на уникальность email
        if (userRepos.existsByEmail(newUser.getEmail())) {
            errors.put("email", "Email is already taken");
        }

        // Проверка на уникальность phone
        if (userRepos.existsByPhone(newUser.getPhone())) {
            errors.put("phone", "Phone number is already taken");
        }

        // Если есть ошибки, возвращаем их
        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        // Если данных уникальны, шифруем пароль и сохраняем пользователя
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepos.save(newUser);

        return ResponseEntity.ok(Map.of("message", "Registration successful"));
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userRepos.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("role", user.getRole());
            response.put("username", user.getUsername());
            response.put("userId", String.valueOf(user.getId())); // Исправлено на userId с маленькой буквы
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
        }
    }
}