package com.ducanh.casestudy.controller;

import com.ducanh.casestudy.model.Coach;
import com.ducanh.casestudy.service.coach.ICoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/coach")
public class CoachController {
    @Autowired
    ICoachService coachService;

//    @GetMapping("/home")
//    public String homeCoach(){
//        return "/coach/home";
//    }

    @GetMapping("/page")
    public ModelAndView find(@PageableDefault(value = 2) Pageable pageable){
        ModelAndView modelAndView =new ModelAndView("/coach");
        Page<Coach> coaches=coachService.findAllPage(pageable);
        modelAndView.addObject("coach",coaches);
        return modelAndView;
    }
}
