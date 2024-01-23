package my.project.msa.user_service.util.mapper;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.msa.user_service.dto.UserDto;
import my.project.msa.user_service.jpa.UserEntity;
import my.project.msa.user_service.vo.RequestUser;
import my.project.msa.user_service.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Slf4j

@Component
@NoArgsConstructor
public class ModelMapperUtil {

    private final ModelMapper modelMapper = new ModelMapper();
    // RequestUser -> UserDto
    public UserDto toUserDto(RequestUser requestUser){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(requestUser, UserDto.class);
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setCreatedAt(new Date());
        return userDto;
    }

    // UserDto -> UserEntity
    public UserEntity toUserEntity(UserDto userDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setEncryptPwd("encrypted");
        return userEntity;
    }

    // UserDto -> ResponseUser
    public ResponseUser toResponseUser(UserDto userDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(userDto, ResponseUser.class);
    }



}
