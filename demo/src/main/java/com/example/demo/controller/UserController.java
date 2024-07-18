package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

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
        return ResponseEntity.ok("Saved");
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
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        User user = userRepository.findByUsername(loginUser.getUsername());
        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}