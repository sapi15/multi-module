package com.multi.module.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @GetMapping
    public String adminH() throws Exception{
        JSONObject json = new JSONObject();
        json.put("admin data1","1");
        json.put("admin data2","2");
        json.put("admin data3","3");

        return json.toString();
    }
}
