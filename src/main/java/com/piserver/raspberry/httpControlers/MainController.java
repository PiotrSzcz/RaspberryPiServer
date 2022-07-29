package com.piserver.raspberry.httpControlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    String getMain(){
        return "main";
    }
}
