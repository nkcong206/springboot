package com.learning.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/all")
    public @ResponseBody
    Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    String getUserById(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return "User Not Found";
        }
        return "User found";
    }

    @PostMapping("/save")
    public @ResponseBody
    Boolean createUser(@RequestBody User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @PostMapping("/login")
    public @ResponseBody
    String checkUser(@RequestBody User user) {
        User abc = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (abc == null) {
            return "khong co tai khoan nay";
        }
        Instant now = Instant.now();
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jwt = Jwts.builder()
                .setSubject("Brian Demers")
                .setAudience("video demo")
                .claim("1d20", new Random().nextInt(20) + 1)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.minus(1, ChronoUnit.MINUTES)))
                .signWith(key)
                .compact();
        return jwt;
    }

    @PostMapping("/delete")
    public @ResponseBody
    Boolean deleteUser(@RequestBody User user) {
        User user1 = userRepository.findByUsername(user.getUsername());
        if (user1 == null) {
            return false;
        }
        userRepository.delete(user1);
        return true;
    }
}
