package com.multi.module;

import com.multi.module.system.spring.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class HomeController {


//    @Autowired
//    private MessageSourceAccessor messageSourceAccessor;

    @Autowired
    MessageUtils messageUtils;


    public HomeController(){
        log.info("HomeController call !!!");
    }

    @GetMapping("/")
    public String home() throws Exception {
        JSONObject json = new JSONObject();
        json.put("data1", "1");
        json.put("data2", "2");
        json.put("data3", "3");
        json.put("Locale", Locale.getDefault());
//        json.put("message ck", messageUtils.getMessage("RESULT_MSG_SUCCESS"));

        return json.toString();
    }


//    @GetMapping("/")
//    public ModelAndView home(){
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("data", "Hello!!!");
//        mav.setViewName("index");
//
////        System.out.println("message ck-> "+messageUtils.getMessage("RESULT_MSG_SUCCESS"));
//
//
//        return mav;
//    }

}
