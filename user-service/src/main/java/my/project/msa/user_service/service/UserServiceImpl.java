package my.project.msa.user_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.dto.request.RequestCreateUser;
import my.project.msa.user_service.dto.request.RequestDeleteUser;
import my.project.msa.user_service.exception.ServiceValidException;
import my.project.msa.user_service.mapper.UserMapper;
import my.project.msa.user_service.persistent.jpa.group.GroupEntity;
import my.project.msa.user_service.persistent.jpa.group.GroupRepository;
import my.project.msa.user_service.persistent.jpa.user.UserEntity;
import my.project.msa.user_service.persistent.jpa.user.UserRepository;
import my.project.msa.user_service.util.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static my.project.msa.user_service.exception.ExceptionHolder.SERVICE_VALID_EX_FAIL_GROUP_FIND;
import static my.project.msa.user_service.exception.ExceptionHolder.SERVICE_VALID_EX_FAIL_USER_FIND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userJpaRepository;
    private final GroupRepository groupRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Transactional
    @Override
    public User createUser(RequestCreateUser request) {
        UserEntity savedEntity = createUserEntity(request);
        return userMapper.fromUserJpaEntity(savedEntity);
    }

    @Transactional
    @Override
    public void deleteUser(RequestDeleteUser request, String userId) {
        Optional<UserEntity> user = userJpaRepository.findByUserId(userId);
        String encodePassword = PasswordEncoder.encodePassword(request.getPwd());

        if (user.isEmpty() || !Objects.equals(user.get().getEncryptPwd(), encodePassword))
            throw new ServiceValidException(SERVICE_VALID_EX_FAIL_USER_FIND);

        UserEntity userEntity = user.get();
        GroupEntity group = userEntity.getGroup();
        group.removeUser(userEntity);
    }

    @Override
    public User getUserByUserId(String userId) {
        Optional<UserEntity> user = userJpaRepository.findByUserId(userId);
        if (user.isEmpty())
            throw new ServiceValidException(SERVICE_VALID_EX_FAIL_USER_FIND);

        return userMapper.fromUserJpaEntity(user.get());
    }

    @Override
    public List<User> getUserByAll() {
        Iterable<UserEntity> users = userJpaRepository.findAll();

        return StreamSupport.stream(users.spliterator(), false)
                .map(userMapper::fromUserJpaEntity)
                .collect(Collectors.toList());
    }

    private UserEntity createUserEntity(RequestCreateUser requestUser) {
        String groupName = requestUser.getGroup();
        GroupEntity groupEntity = groupRepository.findByGroupName(groupName);
        if (groupEntity == null) {
            throw new ServiceValidException(SERVICE_VALID_EX_FAIL_GROUP_FIND);
        }

        UserEntity userEntity = UserEntity.fromRequestCreateUser(requestUser);
        userEntity.setUserId(UUID.randomUUID().toString().substring(0, 8));
        userEntity.setEncryptPwd(PasswordEncoder.encodePassword(requestUser.getPwd()));
        groupEntity.addUser(userEntity);
        return userEntity;
    }
}
