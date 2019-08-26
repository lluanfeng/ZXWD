package com.controller;

import com.pojo.Answers;
import com.pojo.PageBean;
import com.pojo.Questions;
import com.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("con")
public class QuestionsController {
@Autowired
    private QuestionsService service;
@RequestMapping("/show")
    public ModelAndView showQ(ModelAndView mav, PageBean page){
    List<Questions> list = service.showQ(page);
    mav.addObject("page",page);
    mav.addObject("list",list);
    mav.setViewName("show");
    return mav;
    }
    @RequestMapping("add1")
    public String add1(){
    return "add";
    }
    @RequestMapping("add2")
    public String addQ(Questions questions){
        Timestamp tiem = new Timestamp(new Date().getTime());
        questions.setLastmodified(tiem);
        questions.setAnswercount(0);
    int i = service.addQ(questions);
    return "redirect:/con/show";
    }
    @RequestMapping("/upShow")
    public ModelAndView upshowOne(Integer id,ModelAndView mav){
    Questions questions = service.showOne(id);
    List<Answers> list = service.showQid(id);
    mav.addObject("list",list);
    mav.addObject("questions",questions);
    mav.setViewName("update");
    return mav;
    }
    @RequestMapping("add3")
    public String addQ(Answers answers){
        Timestamp tiem = new Timestamp(new Date().getTime());
        answers.setAnsdate(tiem);
        int i = service.addA(answers);
        return "redirect:/con/show";
    }
}
