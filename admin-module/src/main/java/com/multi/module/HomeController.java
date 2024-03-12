package com.multi.module;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
public class HomeController {

    public HomeController(){
        log.info("HomeController call !!!");
    }

    @GetMapping(value = {"/", "index.do"})
    public ModelAndView home(){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("/index");

        return mav;
    }

}
