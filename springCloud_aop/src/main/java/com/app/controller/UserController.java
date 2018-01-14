package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.server.TestService;

@RestController
public class UserController  {
    @Autowired
    private TestService testService;
    @RequestMapping(value="/fuck/{num}")
    public void updateUser(@PathVariable Integer num) {
    	System.out.println("=================fck");
    	testService.addData(num);
    }
	
}
