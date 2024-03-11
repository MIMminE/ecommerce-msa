package my.project.msa.user_service.service;

import my.project.msa.user_service.dto.UserDto;
import my.project.msa.user_service.jpa.UserEntity;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    Iterable<UserDto> getUserByAll();
}
