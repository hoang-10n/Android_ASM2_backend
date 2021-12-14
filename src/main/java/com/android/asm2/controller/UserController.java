package com.android.asm2.controller;

import com.android.asm2.model.User;
import com.android.asm2.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

    @RequestMapping(path = "/api/users", method = RequestMethod.POST)
    public boolean addUser(@RequestBody User user) {
        return userService.addUser(user);
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

    @RequestMapping(path = "/init/users", method = RequestMethod.GET)
    public void initUserDB() throws IOException {
        String sURL = "https://my-json-server.typicode.com/hoang-10n/Android_ASM2/users";
        URL userDB = new URL(sURL);

        StringBuilder sb = new StringBuilder();
        String line;

        HttpURLConnection connection = (HttpURLConnection) userDB.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }

        userService.deleteAll();
        try {
            JSONArray array = JSONFactoryUtil.createJSONArray(sb.toString());
            for (int i = 0; i < array.length(); i++) {
                userService.saveUser(new Gson().fromJson(array.getJSONObject(i).toString(), User.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
