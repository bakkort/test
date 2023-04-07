package com.example.Gifts.controller;

import com.example.Gifts.model.User;
import com.example.Gifts.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/")
    public List<User> getAllUser() {
        return UserService.userDataBase;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return UserService.getUser(id);
    }

    @PostMapping("/addUser")
    public List<User> addUser(@RequestBody User user) {
        return UserService.addUser(user);
    }

    @PutMapping("/updateUser")
    public List<User> updateUser(@RequestBody User user) {
        return UserService.updateUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public List<User> deleteUser(@PathVariable int id) {
        return UserService.deleteUser(id);
    }
}
