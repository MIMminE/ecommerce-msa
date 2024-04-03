package my.project.msa.user_service.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.dto.response.ResponseUser;
import my.project.msa.user_service.mapper.UserDomainMapper;
import my.project.msa.user_service.service.UserService;
import my.project.msa.user_service.dto.request.RequestCreateUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
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
        User user = userMapper.fromRequestCreateUser(requestCreateUser);
        User createdUser = userService.createUser(user);
        ResponseUser responseUser = userMapper.toResponseUser(createdUser);

        return new ResponseEntity<>(responseUser,HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers(){
        Iterable<User> users = userService.getUserByAll();

        return ResponseEntity.ok().body(
                StreamSupport.stream(users.spliterator(), false)
                .map(userMapper::toResponseUser)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable String userId){
        return ResponseEntity.ok().body(
                userMapper.toResponseUser(userService.getUserByUserId(userId))
        );
    }
}
