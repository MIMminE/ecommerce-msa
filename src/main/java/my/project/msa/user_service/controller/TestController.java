package my.project.msa.user_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-service")
public class TestController {
    @GetMapping("hello")
    public String TestHello(){
        return "Hello";
    }
}
