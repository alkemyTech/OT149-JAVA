package com.alkemy.ong.Controllers;

import com.alkemy.ong.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, ModelMap model) throws Exception {

        User user =  userService.checkPass(username,password);

        if (user != null){
            model.put("user", user);
        } else {
            model.put("ok", false);
        }

        return //html;

    }
