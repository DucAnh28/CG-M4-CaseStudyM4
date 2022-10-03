package com.ducanh.casestudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping()
    public String home(){
        return "/homepage/home";
    }
    @GetMapping("/blog")
    public ModelAndView blog(){
        ModelAndView modelAndView = new ModelAndView("/homepage/blog");
        return modelAndView;
    }
}
