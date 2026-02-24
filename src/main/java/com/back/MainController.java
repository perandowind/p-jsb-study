package com.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    @ResponseBody // 응답본문을 의미
    public String index() {
        return "index";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
