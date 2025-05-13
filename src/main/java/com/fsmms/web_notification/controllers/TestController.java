package com.fsmms.web_notification.controllers;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TestController {
    //@PathVariable String admin, @PathVariable String id,
    @GetMapping("/{user}")
    public String loadPage(@PathVariable String user, Model model, HttpSession session){
        model.addAttribute("user", user);
        return "demo";
    }
}
