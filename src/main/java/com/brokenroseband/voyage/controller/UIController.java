package com.brokenroseband.voyage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UIController
{

    @RequestMapping(value={"/login", "/registration", "/profile","/","/tours","/tours/{id}"})
    public String HomePage() {
        return "index.html";
    }
}