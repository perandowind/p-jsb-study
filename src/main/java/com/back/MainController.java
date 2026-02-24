package com.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/index")
    public void index() {
        System.out.println("index");
    }

    @GetMapping("/hello")
    public void hello() {
        System.out.println("hello()");
    }
}
