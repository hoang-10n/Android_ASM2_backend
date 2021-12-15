package com.android.asm2.controller;

import com.android.asm2.model.User;
import com.android.asm2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/api/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(path = "/api/users", method = RequestMethod.DELETE)
    public void deleteAllUsers() {
        userService.deleteAll();
    }

    @RequestMapping(path = "/api/users", method = RequestMethod.POST)
    public boolean addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(path = "/api/users/many", method = RequestMethod.POST)
    public boolean addManyUsers(@RequestBody User[] users) {
        try {
            for (User user : users) userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @RequestMapping(path = "/api/users", method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping(path = "/api/users/{zoneId}", method = RequestMethod.DELETE)
    public User deleteUser(@PathVariable String zoneId) {
        return userService.deleteUserByZoneId(zoneId);
    }

    @RequestMapping(path = "/api/users/{zoneId}", method = RequestMethod.GET)
    public User getUserByZoneId(@PathVariable String zoneId) {
        return userService.getUserByUsername(zoneId);
    }
}
