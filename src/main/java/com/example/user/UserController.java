package com.example.user;



import com.example.location.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users")
    public List<User> getAllUSers(){
        return userService.getAllUser();
    }

    @RequestMapping(value = "users/{id}")
    public User getUser(@PathVariable String id){
        return userService.getUser(id);

    }







}
