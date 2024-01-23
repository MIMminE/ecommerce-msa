package my.project.msa.user_service.service;

import lombok.AllArgsConstructor;
import my.project.msa.user_service.dto.UserDto;
import my.project.msa.user_service.jpa.UserEntity;
import my.project.msa.user_service.jpa.UserRepository;
import my.project.msa.user_service.util.mapper.ModelMapperUtil;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    ModelMapperUtil modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = modelMapper.toUserEntity(userDto);
        userRepository.save(userEntity);
        return null;
    }
}
