package my.project.msa.user_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.mapper.UserDomainMapper;
import my.project.msa.user_service.persistent.jpa.UserJpaEntity;
import my.project.msa.user_service.persistent.jpa.UserJpaRepository;
import my.project.msa.user_service.util.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserJpaRepository userJpaRepository;
    private final UserDomainMapper userMapper = UserDomainMapper.INSTANCE;

    @Override
    public User createUser(User user) {
        UserJpaEntity userJpaEntity = userMapper.toUserJpaEntity(user);
        setUserId(userJpaEntity);
        userJpaEntity.setEncryptPwd(PasswordEncoder.encodePassword(user.getPwd()));
        UserJpaEntity savedEntity = userJpaRepository.save(userJpaEntity);
        return userMapper.fromUserJpaEntity(savedEntity);
    }

    @Override
    public User getUserByUserId(String userId) {
        UserJpaEntity userJpaEntity = userJpaRepository.findByUserId(userId);
        return userMapper.fromUserJpaEntity(userJpaEntity);
    }

    @Override
    public List<User> getUserByAll() {
        Iterable<UserJpaEntity> entities = userJpaRepository.findAll();

        return StreamSupport.stream(entities.spliterator(), false)
                .map(userMapper::fromUserJpaEntity)
                .collect(Collectors.toList());
    }

    private void setUserId(UserJpaEntity userJpaEntity) {
        String randomUserId = UUID.randomUUID().toString().substring(0,8);
        userJpaEntity.setUserId(randomUserId);
    }
}
