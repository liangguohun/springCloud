package com.homefun.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homefun.beans.UserInfo;
import com.homefun.services.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/findUser/{userName}")
    public UserInfo findUser(@PathVariable String userName ) {
    	return userService.findUserInfo(userName);
    }

}
