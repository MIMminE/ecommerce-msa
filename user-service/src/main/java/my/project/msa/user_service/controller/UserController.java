package my.project.msa.user_service.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.dto.request.RequestCreateUser;
import my.project.msa.user_service.dto.request.RequestDeleteUser;
import my.project.msa.user_service.dto.response.ResponseUsers;
import my.project.msa.user_service.dto.response.ResponseUser;
import my.project.msa.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/health_check")
    public String status(HttpServletRequest request) {
        return String.format("It's Working in User Service %s", request.getServerPort());
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody @Valid RequestCreateUser request) {
        User createdUser = userService.createUser(request);
        ResponseUser responseUser = ResponseUser.fromUserDomain(createdUser);
        // TODO ResponseUser Orders 정보 추가 작업 필요

        URI location = URI.create(String.format("/user-service/users/%s", responseUser.getUserId()));
        return ResponseEntity.created(location).body(responseUser);
    }

    @PostMapping("/users/{userId}/remove")
    public ResponseEntity<ResponseUser> deleteUser(@PathVariable String userId, @RequestBody RequestDeleteUser request) {
        User user = userService.deleteUser(request, userId);
        return ResponseEntity.ok().body(ResponseUser.fromUserDomainForRemove(user));
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseUsers> getUsers() {
        Iterable<User> users = userService.getUserByAll();
        ResponseUsers ResponseUsers = new ResponseUsers(StreamSupport.stream(users.spliterator(), false)
                .map(ResponseUser::fromUserDomain)
                .toList());
        return new ResponseEntity<>(ResponseUsers, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable String userId) {
        User user = userService.getUserByUserId(userId);
        return ResponseEntity.ok().body(ResponseUser.fromUserDomain(user));
    }
}
