package com.multi.module.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/user")
public class UserController {

//    @GetMapping("/user")
//    public ModelAndView managerP() {
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("userPage");
//
//        return mav;
//    }

    @GetMapping("/")
    public String adminH() throws Exception{
        JSONObject json = new JSONObject();
        json.put("user data1","1");
        json.put("user data2","2");
        json.put("user data3","3");

        return json.toString();
    }

}
