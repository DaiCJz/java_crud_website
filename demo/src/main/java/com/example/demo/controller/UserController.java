package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/demo/add")
    public ResponseEntity<String> addNewUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String location,
            @RequestParam String gender,
            @RequestParam String hobbies,
            @RequestParam String introduction) {

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setLocation(location);
        newUser.setGender(gender);
        newUser.setHobbies(hobbies);
        newUser.setIntroduction(introduction);

        userRepository.save(newUser);
        return ResponseEntity.ok("User saved successfully");
    }

    @PostMapping("/check-username")
    public ResponseEntity<Map<String, Boolean>> checkUsername(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        boolean exists = userRepository.existsByUsername(username);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpSession session) {
        User dbUser = userService.findByUsername(user.getUsername());
        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            session.setAttribute("user", dbUser);
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping("/check-login-status")
    public ResponseEntity<Map<String, Object>> checkLoginStatus(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Map<String, Object> response = new HashMap<>();
        response.put("loggedIn", user != null);
        response.put("username", user != null ? user.getUsername() : null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username, HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser != null) {
            User user = userService.findByUsername(username);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/session-username")
    public ResponseEntity<String> getSessionUsername(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return ResponseEntity.ok(user.getUsername());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
