package com.capstone.service1.Services;

import com.capstone.service1.Models.UserModel;
import com.capstone.service1.Repositary.UserRepositary;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepositary userRepository;

    // Method to retrieve all users
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean userExists(long userNumber) {
        Optional<UserModel> user = userRepository.getByUserNumber(userNumber);
        return user.isPresent();
    }

    public String getEmailByNumber(long mobileNumber) {
        UserModel user=  userRepository.getByUserNumber(mobileNumber).orElse(null);
        return user.getUser_Email();
    }



}