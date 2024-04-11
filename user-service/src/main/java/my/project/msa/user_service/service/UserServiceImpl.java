package my.project.msa.user_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.dto.request.RequestCreateUser;
import my.project.msa.user_service.dto.request.RequestDeleteUser;
import my.project.msa.user_service.exception.ServiceValidException;
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
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userJpaRepository;
    private final GroupRepository groupRepository;


    @Override
    public User createUser(RequestCreateUser request) {
        UserEntity savedEntity = createUserEntity(request);
        return UserEntity.toUserDomain(savedEntity);
    }

    @Override
    public User deleteUser(RequestDeleteUser request, String userId) {
        Optional<UserEntity> user = userJpaRepository.findByUserId(userId);
        String encodePassword = PasswordEncoder.encodePassword(request.getPwd());

        if (user.isEmpty() || !Objects.equals(user.get().getEncryptPwd(), encodePassword))
            throw new ServiceValidException(SERVICE_VALID_EX_FAIL_USER_FIND);

        UserEntity userEntity = user.get();
        GroupEntity group = userEntity.getGroup();
        User result = UserEntity.toUserDomain(userEntity);
        group.removeUser(userEntity);
        return result;
    }

    @Override
    public User getUserByUserId(String userId) {
        Optional<UserEntity> userEntity = userJpaRepository.findByUserId(userId);
        if (userEntity.isEmpty())
            throw new ServiceValidException(SERVICE_VALID_EX_FAIL_USER_FIND);

        return UserEntity.toUserDomain(userEntity.get());
    }

    @Override
    public List<User> getUserByAll() {
        Iterable<UserEntity> users = userJpaRepository.findAll();

        return StreamSupport.stream(users.spliterator(), false)
                .map(UserEntity::toUserDomain)
                .collect(Collectors.toList());
    }

    @Transactional
    protected UserEntity createUserEntity(RequestCreateUser requestUser) {
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
