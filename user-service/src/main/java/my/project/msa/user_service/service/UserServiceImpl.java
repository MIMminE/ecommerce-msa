package my.project.msa.user_service.service;

import lombok.AllArgsConstructor;
import my.project.msa.user_service.dto.UserDto;
import my.project.msa.user_service.jpa.UserEntity;
import my.project.msa.user_service.jpa.UserRepository;
import my.project.msa.user_service.util.mapper.ModelMapperUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    ModelMapperUtil modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = modelMapper.toUserEntity(userDto);
        userRepository.save(userEntity);
        return null;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        return modelMapper.toUserDto(userEntity);
    }

    @Override
    public List<UserDto> getUserByAll() {
        Iterable<UserEntity> entities = userRepository.findAll();
        List<UserDto> result = new ArrayList<>();
        entities.forEach(e->
                result.add(modelMapper.toUserDto(e)));

        return result;
    }
}
