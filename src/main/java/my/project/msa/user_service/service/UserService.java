package my.project.msa.user_service.service;

import my.project.msa.user_service.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
}
