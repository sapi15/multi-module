package com.multi.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin/login")
public class LoginController {

    @GetMapping("/page.do")
    public ModelAndView loginP(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("loginPage");

        return mav;
    }
}
