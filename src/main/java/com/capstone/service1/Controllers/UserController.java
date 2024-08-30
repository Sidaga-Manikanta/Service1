package com.capstone.service1.Controllers;

import com.capstone.service1.Services.ConnectWithUsEmail;
import com.capstone.service1.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    ConnectWithUsEmail connectWithUsEmail;


    static long currentUser;

    @GetMapping("/auth")
    public boolean checkUser(@RequestParam("mobileNumber")long userNumber, HttpServletRequest request) {
        boolean exists = userService.userExists(userNumber);
        if (exists) {
            HttpSession session = request.getSession();
            session.setAttribute("user", userNumber);
            System.out.println("session created with id "+ session.getId()+ "current user"+session.getAttribute("user"));
        }
        return exists;

    }
    @GetMapping("/currentUser")
    public long getCurrentUserI(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        session.getAttribute("user");
        if (session != null) {
            currentUser = (long) session.getAttribute("user");
            return currentUser;
        }
        return 0;
    }

    @PostMapping("/connectWithUs")
    public String sendingConnectionMail(){
        connectWithUsEmail.sendConnectionEmail("s.mani.k1437@gmail.com","about request to our service","the user with details ..want to connect with us");
        return "email sent successfully";
        
    }
}
