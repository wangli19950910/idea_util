package com.smvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class logoutAction {

    @RequestMapping("/set")
    public String set(HttpSession session){
        session.setAttribute("set","set123");
        return "redirect:check";
    }
}
