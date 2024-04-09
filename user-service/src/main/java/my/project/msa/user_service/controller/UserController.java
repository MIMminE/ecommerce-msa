package my.project.msa.user_service.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.dto.request.RequestCreateUser;
import my.project.msa.user_service.dto.request.RequestDeleteUser;
import my.project.msa.user_service.dto.response.ResponseUsers;
import my.project.msa.user_service.dto.response.ResponseUser;
import my.project.msa.user_service.mapper.UserMapper;
import my.project.msa.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @GetMapping("/health_check")
    public String status(HttpServletRequest request){
        return String.format("It's Working in User Service %s", request.getServerPort());
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody @Valid RequestCreateUser request){
        User createdUser = userService.createUser(request);
        ResponseUser responseUser = userMapper.toResponseUser(createdUser);
        return new ResponseEntity<>(responseUser,HttpStatus.CREATED);
    }

    @PostMapping("/users/{userId}/remove")
    public ResponseEntity<?> deleteUser(@PathVariable String userId, @RequestBody RequestDeleteUser request){
        userService.deleteUser(request, userId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/users")
    public ResponseEntity<ResponseUsers> getUsers(){
        Iterable<User> users = userService.getUserByAll();
        ResponseUsers listResponseUser = new ResponseUsers(StreamSupport.stream(users.spliterator(), false)
                .map(userMapper::toResponseUser)
                .toList());
        return new ResponseEntity<>(listResponseUser, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable String userId){
        return ResponseEntity.ok().body(
                userMapper.toResponseUser(userService.getUserByUserId(userId))
        );
    }
}
