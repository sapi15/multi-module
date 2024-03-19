package com.multi.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login.do")
    public ModelAndView loginP(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("loginPage");

        return mav;
    }
}
