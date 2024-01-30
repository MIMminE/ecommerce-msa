package my.project.msa.user_service.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import my.project.msa.user_service.dto.UserDto;
import my.project.msa.user_service.service.UserService;
import my.project.msa.user_service.util.mapper.ModelMapperUtil;
import my.project.msa.user_service.vo.Greeting;
import my.project.msa.user_service.vo.RequestUser;
import my.project.msa.user_service.vo.ResponseUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user-service")
@AllArgsConstructor
public class UserController {

//    private Environment env;
    private Greeting greeting;
    private ModelMapperUtil modelmapper;
    private UserService userService;

    @GetMapping("/health_check")
    public String status(HttpServletRequest request){
        return String.format("It's Working in User Service %s", request.getServerPort());
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

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers(){
        Iterable<UserDto> userByAll = userService.getUserByAll();
        List<ResponseUser> result = new ArrayList<>();
        userByAll.forEach(v ->
                result.add(modelmapper.toResponseUser(v)));

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable String userId){
        ResponseUser result = modelmapper.toResponseUser(userService.getUserByUserId(userId));

        return ResponseEntity.ok().body(result);
    }
}
