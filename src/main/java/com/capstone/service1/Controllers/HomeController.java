package com.capstone.service1.Controllers;

import com.capstone.service1.Models.UserModel;
import com.capstone.service1.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/allUsers")
    public  List<UserModel> allUsers() {
        return userService.getAllUsers();
    }
}
