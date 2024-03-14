package com.multi.module;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class HomeController {
    public HomeController(){
        log.info("HomeController call !!!");
    }


    @GetMapping("/")
    public ModelAndView Home(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("data","Hello!!!");
        mav.setViewName("/index");

        return mav;
    }

}
