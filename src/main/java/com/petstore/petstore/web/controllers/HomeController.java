package com.petstore.petstore.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/welcome")
    // tell the spring that response is a String
    public @ResponseBody
    String welcomeMessage() {
        return "Pet Store Application Running!! yeah";
    }



    @GetMapping("/page")
    public String displayWelcomePage() {
        return "welcome";
    }

}