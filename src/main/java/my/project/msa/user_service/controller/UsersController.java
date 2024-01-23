package my.project.msa.user_service.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import my.project.msa.user_service.dto.UserDto;
import my.project.msa.user_service.service.UserService;
import my.project.msa.user_service.util.mapper.ModelMapperUtil;
import my.project.msa.user_service.vo.Greeting;
import my.project.msa.user_service.vo.RequestUser;
import my.project.msa.user_service.vo.ResponseUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-service")
@AllArgsConstructor
public class UsersController {

//    private Environment env;
    private Greeting greeting;
    private ModelMapperUtil modelmapper;
    private UserService userService;

    @GetMapping("/health_check")
    public String status(){
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome(){
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user){
        UserDto userDto = modelmapper.toUserDto(user);
        userService.createUser(userDto);
        ResponseUser responseUser = modelmapper.toResponseUser(userDto);
        return new ResponseEntity<ResponseUser>(responseUser,HttpStatus.CREATED);
    }
}
