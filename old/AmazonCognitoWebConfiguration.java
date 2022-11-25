package com.example.springtest;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AmazonCognitoWebConfiguration implements WebMvcConfigurer {

    @RequestMapping("/AmazonCognito")
    public ModelAndView AmazonCognito () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("logout.html");
        return modelAndView;
    }

    // @Override
    // public void addViewControllers(ViewControllerRegistry registry) {
    //     registry.addViewController("/").setViewName("home");
    // }
}