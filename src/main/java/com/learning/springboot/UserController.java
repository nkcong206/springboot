package com.learning.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;
// import java.security.Key;

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

    @PostMapping("/create")
    public @ResponseBody
    Boolean createUser(@RequestBody User user) {
        User abc = userRepository.findByUsername(user.getUsername());
        try {
            userRepository.save(abc);
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
        return "co tai khoan nay";
    }

    @PostMapping("/delete")
    public @ResponseBody
    Boolean deleteUser(@RequestBody User user) {
        User abc = userRepository.findByUsername(user.getUsername());
        if (abc == null) {
            return false;
        }
        userRepository.delete(abc);
        return true;
    }

    @PostMapping("/update")
    public @ResponseBody Boolean updateUser(@RequestBody ChangePassword changePassword){
        User abc = userRepository.findByUsernameAndPassword(changePassword.getUsername(), changePassword.getOld_password());
        if(abc == null){
            return false;
        }
        abc.setPassword(changePassword.getNew_password());
        userRepository.save(abc);
        return true;
    }

}
