//package my.project.msa.user_service.service;
//
//import lombok.extern.slf4j.Slf4j;
//import my.project.msa.user_service.domain_model.User;
//import my.project.msa.user_service.dto.request.RequestCreateUser;
//import my.project.msa.user_service.persistent.jpa.Group.GroupEntity;
//import my.project.msa.user_service.persistent.jpa.Group.GroupRepository;
//import my.project.msa.user_service.persistent.jpa.user.UserEntity;
//import my.project.msa.user_service.persistent.jpa.user.UserRepository;
//import org.assertj.core.api.Assertions;
//import org.assertj.core.groups.Tuple;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.UUID;
//
//import static my.project.msa.user_service.util.PasswordEncoder.encodePassword;
//import static org.mockito.ArgumentMatchers.argThat;
//import static org.mockito.BDDMockito.given;
//
//@Slf4j
//@DisplayName("UserService 테스트")
//@ExtendWith(MockitoExtension.class)
//class UserServiceImplTest {
//
//    @Mock
//    UserRepository userJpaRepository;
//
//    @Mock
//    GroupRepository groupRepository;
//
//    @Test
//    @DisplayName("createUser 메서드를 호출하면 레파지토리 save 메서드 호출, 패스워드 암호화 처리를 성공적으로 진행한다.")
//    void createUser() {
//
//        // given
//        GroupEntity Group = GroupEntity.builder()
//                .groupName("GroupName")
//                .leaderName("LeaderName")
//                .leaderNumber("010-1234-5678")
//                .build();
//
//        UserService userService = new UserServiceImpl(userJpaRepository, groupRepository);
//        String testPwd = "testPwd";
//        UserEntity userJpaEntity = createTestingSample(testPwd, Group);
//
//        given(userJpaRepository.save(
//                argThat(entity -> userJpaEntity.getEncryptPwd().equals(entity.getEncryptPwd()))
//        )).willReturn(userJpaEntity);
//
//        given(groupRepository.findByGroupName("GroupName")).willReturn(Group);
//
//        RequestCreateUser request = RequestCreateUser.builder()
//                .name(userJpaEntity.getName())
//                .pwd(testPwd)
//                .email(userJpaEntity.getEmail())
//                .Group("GroupName")
//                .build();
//
//        // when
//        User resultUser = userService.createUser(request);
//
//        // then
//        Assertions.assertThat(resultUser)
//                .extracting("email", "name", "pwd", "userId", "Group")
//                .contains(userJpaEntity.getEmail(), userJpaEntity.getName(), null, userJpaEntity.getUserId(), Group);
//    }
//
//
//    @DisplayName("getUserByUserId 메서드를 호출하면 레파지토리 findByUserId 호출에 성공한다.")
//    @Test
//    void getUserByUserId() {
//        // given
//        UserService userService = new UserServiceImpl(userJpaRepository, departmentJpaRepository);
//        String userEmail = "testEmail";
//        String userName = "testName";
//        String userId = "testUserId";
//        String userPwd = "testPwd";
//        String encodePwd = encodePassword(userPwd);
//
//        given(userJpaRepository.findByUserId(userId))
//                .willReturn(
//                        UserEntity.builder()
//                                .email(userEmail)
//                                .name(userName)
//                                .userId(userId)
//                                .encryptPwd(encodePwd)
//                                .build()
//                );
//
//        // when
//        User user = userService.getUserByUserId(userId);
//
//        // then
//        Assertions.assertThat(user)
//                .extracting("email", "name", "userId")
//                .contains(userEmail, userName, userId);
//    }
//
//
//    @DisplayName("getUserByAll 메서드를 호출하면 레파지토리 findAll 호출에 성공한다.")
//    @Test
//    void getUserByAll() {
//        // given
//        UserService userService = new UserServiceImpl(userJpaRepository, departmentJpaRepository);
//
//        String password1 = "pwd1";
//        String password2 = "pwd2";
//        UserEntity sampleEntity1 = createTestingSample(password1);
//        UserEntity sampleEntity2 = createTestingSample(password2);
//
//        given(userJpaRepository.findAll())
//                .willReturn(List.of(sampleEntity1, sampleEntity2));
//
//        // when
//        Iterable<User> allUser = userService.getUserByAll();
//
//        // then
//        Assertions.assertThat(allUser)
//                .extracting("email", "name", "pwd", "userId")
//                .contains(
//                        Tuple.tuple(sampleEntity1.getEmail(), sampleEntity1.getName(), null, sampleEntity1.getUserId()),
//                        Tuple.tuple(sampleEntity2.getEmail(), sampleEntity2.getName(), null, sampleEntity2.getUserId())
//                );
//    }
//
//    private UserEntity createTestingSample(String pwd, GroupEntity Group) {
//        return UserEntity.builder()
//                .email(createSampleEmail())
//                .name(UUID.randomUUID().toString().substring(0, 8))
//                .userId(UUID.randomUUID().toString().substring(0, 8))
//                .encryptPwd(encodePassword(pwd))
//                .Group(Group)
//                .build();
//    }
//
//    private String createSampleEmail() {
//        String domain = "@example.com";
//        String username = UUID.randomUUID().toString().substring(0, 8);
//        return username + domain;
//    }
//}