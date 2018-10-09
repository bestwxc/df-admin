package com.df4j.module.admin.controller;


import com.df4j.base.server.Result;
import com.df4j.base.utils.ResultUtils;
import com.df4j.module.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class UserExtController extends UserController{
    private Logger logger = LoggerFactory.getLogger(UserExtController.class);
    @Autowired
    private UserService userService;


    @RequestMapping("/image/code")
    public Result imageCode(@RequestBody Map<String,?> map) {

        return ResultUtils.success(null);
    }



    @RequestMapping("/user/login")
    public Result login(@RequestBody Map<String,?> map) {

        return ResultUtils.success(null);
    }

    @RequestMapping("/user/passwd")
    public Result passwd(@RequestBody Map<String,?> map) {

        return ResultUtils.success(null);
    }
}
