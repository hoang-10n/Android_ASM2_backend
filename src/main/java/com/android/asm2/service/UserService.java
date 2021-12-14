package com.android.asm2.service;

import com.android.asm2.model.User;
import com.android.asm2.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepo.findById(username);
        return userOptional.orElse(null);
    }

    public boolean addUser(User user) {
        if (getUserByUsername(user.getUsername()) == null) {
            saveUser(user);
            return true;
        }
        return false;
    }

    public boolean updateUser(User user) {
        if (getUserByUsername(user.getUsername()) != null) {
            saveUser(user);
            return true;
        }
        return false;
    }

    public User deleteUserByZoneId(String username) {
        User user = getUserByUsername(username);
        userRepo.delete(user);
        return user;
    }


    public void deleteAll() {
        userRepo.deleteAll();
    }
}
