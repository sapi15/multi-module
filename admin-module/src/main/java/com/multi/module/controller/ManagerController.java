package com.multi.module.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/manager")
public class ManagerController {

//    @GetMapping("/manager")
//    public ModelAndView managerP() {
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("managerPage");
//
//        return mav;
//    }

    @GetMapping
    public String adminH() throws Exception{
        JSONObject json = new JSONObject();
        json.put("manager data1","1");
        json.put("manager data2","2");
        json.put("manager data3","3");

        return json.toString();
    }
}
