package my.project.msa.user_service.util.mapper;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.msa.user_service.dto.UserDto;
import my.project.msa.user_service.jpa.UserEntity;
import my.project.msa.user_service.vo.RequestUser;
import my.project.msa.user_service.vo.ResponseOrder;
import my.project.msa.user_service.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j

@Component
@RequiredArgsConstructor
public class ModelMapperUtil {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper = new ModelMapper();

    // RequestUser -> UserDto
    public UserDto toUserDto(RequestUser requestUser) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(requestUser, UserDto.class);
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setCreatedAt(new Date());
        return userDto;
    }

    // UserDto -> UserEntity
    public UserEntity toUserEntity(UserDto userDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setEncryptPwd(bCryptPasswordEncoder.encode(userDto.getPwd()));
        return userEntity;
    }

    // UserEntity -> UserDto
    public UserDto toUserDto(UserEntity userEntity) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(userEntity, UserDto.class);
    }

    // UserDto -> ResponseUser
    public ResponseUser toResponseUser(UserDto userDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<ResponseOrder> orders = new ArrayList<>();
        // TODO
        ResponseUser responseUser = modelMapper.map(userDto, ResponseUser.class);
        if (!orders.isEmpty()) responseUser.setOrders(orders);
        return responseUser;
    }

    // UserEntity -> ResponseUser
    public ResponseUser toResponseUser(UserEntity userEntity) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(userEntity, ResponseUser.class);
    }
}
