package com.webProject.webApp.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String greet(){
        return "Welcome to Home";
    }

    @RequestMapping("/about")
    public String about(){
        return "We dont have ABOUT";
    }
}
