package my.project.msa.user_service.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.dto.request.RequestCreateUser;
import my.project.msa.user_service.dto.response.ListResponseUser;
import my.project.msa.user_service.dto.response.ResponseUser;
import my.project.msa.user_service.mapper.UserDomainMapper;
import my.project.msa.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDomainMapper userMapper = UserDomainMapper.INSTANCE;

    @GetMapping("/health_check")
    public String status(HttpServletRequest request){
        return String.format("It's Working in User Service %s", request.getServerPort());
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody @Valid RequestCreateUser requestCreateUser){
        User createdUser = userService.createUser(requestCreateUser);
        ResponseUser responseUser = userMapper.toResponseUser(createdUser);
        System.out.println(responseUser);
        return new ResponseEntity<>(responseUser,HttpStatus.CREATED);
    }


    @GetMapping("/users")
    public ResponseEntity<ListResponseUser> getUsers(){
        Iterable<User> users = userService.getUserByAll();
        ListResponseUser listResponseUser = new ListResponseUser(StreamSupport.stream(users.spliterator(), false)
                .map(userMapper::toResponseUser)
                .toList());
        System.out.println(listResponseUser);
        return new ResponseEntity<>(listResponseUser, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable String userId){
        return ResponseEntity.ok().body(
                userMapper.toResponseUser(userService.getUserByUserId(userId))
        );
    }

    @GetMapping("/test")
    public ResponseEntity<List<String>> test(){
        return ResponseEntity.ok(List.of("test","test"));
    }
}
