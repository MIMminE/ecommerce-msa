package my.project.msa.user_service.controller;

import lombok.NoArgsConstructor;
import my.project.msa.user_service.config.messages.Greeting;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@NoArgsConstructor
public class UsersController {

//    private Environment env;
    private Greeting greeting;

    @GetMapping("/health_check")
    public String status(){
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome(){
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }
}
