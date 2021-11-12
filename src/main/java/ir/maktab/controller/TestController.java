package ir.maktab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/test/ajax_test")
    public String testPage(){
        return "test";
    }
}
