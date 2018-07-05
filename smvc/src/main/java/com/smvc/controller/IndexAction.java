package com.smvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexAction {

    @RequestMapping("/check")
    public String check(HttpSession session){
        Integer i = (Integer)session.getAttribute("count");
        if(i==null){
            i=0;
        }
        i++;
        session.setAttribute("count",i);

        System.out.println(session.getAttribute("name"));
        System.out.println(session.getAttribute("set"));
        return "check";
    }
    @RequestMapping("/clear")
    public String clear(HttpSession session){

        session.setAttribute("count",0);
        return "check";
    }

}
