package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.UserCreationRequest;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
    //Controller sẽ gọi xuống service
    @Autowired
    private UserService userService;

    //Khai báo endpoint với method post
    @PostMapping("/users")
    //Map data request vào object
    User createUser(@RequestBody UserCreationRequest request) {
        return userService.createUser(request);
    }
}
