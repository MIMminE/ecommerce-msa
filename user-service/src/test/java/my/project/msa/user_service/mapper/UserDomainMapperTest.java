//package my.project.msa.user_service.mapper;
//
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.Validation;
//import jakarta.validation.Validator;
//import my.project.msa.user_service.domain_model.User;
//import my.project.msa.user_service.dto.request.RequestCreateUser;
//import my.project.msa.user_service.dto.response.ResponseUser;
//import my.project.msa.user_service.persistent.jpa.user.UserEntity;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.Set;
//
//import static my.project.msa.user_service.persistent.jpa.user.UserEntity.*;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DisplayName("UserMapper 테스트")
//class UserDomainMapperTest {
//
//    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//    UserDomainMapper userDomainMapper = UserDomainMapper.INSTANCE;
//
//    @Test
//    @DisplayName("toResponseUser 메서드로 [User] -> [ResponseUser] 변환에 성공한다.")
//    void toResponseUser() {
//        // given
//        LocalDate now = LocalDate.now();
//
//        User user = User.builder()
//                .userId("testUser")
//                .pwd("testPassword")
//                .name("testName")
//                .email("testEmail")
//                .createdAt(Date.valueOf(now))
//                .build();
//
//        // when
//        ResponseUser responseUser = userDomainMapper.toResponseUser(user);
//
//        // then
//        assertThat(responseUser)
//                .extracting("email", "name", "userId", "orders")
//                .contains("testEmail", "testName", "testUser", null);
//
//    }
//
//    @Test
//    @DisplayName("toUserJpaEntity 메서드로 [User] -> [UserJpaEntity] 변환에 성공한다.")
//    void toUserJpaEntity() {
//        // given
//        Date date = Date.valueOf(LocalDate.now());
//
//        User user = User.builder()
//                .userId("testUser")
//                .pwd("testPassword")
//                .name("testName")
//                .email("testEmail")
//                .createdAt(date)
//                .build();
//
//        // when
//        UserEntity userJpaEntity = userDomainMapper.toUserJpaEntity(user);
//        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userJpaEntity);
//        ConstraintViolation<UserEntity> violation = violations.iterator().next();
//
//        // then
//        assertThat(violation.getMessage()).isEqualTo(ENCRYPT_PWD_NUT_NULL);
//        assertThat(violations).hasSize(1);
//
//        assertThat(userJpaEntity)
//                .extracting("email","name","userId","encryptPwd", "createdAt")
//                .contains("testEmail","testName","testUser",null, null);
//    }
//
//    @Test
//    @DisplayName("fromUserJpaEntity 메서드로 [UserJpaEntity] -> [User] 변환에 성공한다.")
//    void fromUserJpaEntity() {
//        // given
//        Date date = Date.valueOf(LocalDate.now());
//        UserEntity userJpaEntity = builder()
//                .email("testEmail")
//                .name("testName")
//                .userId("testUser")
//                .encryptPwd("testPwd")
//                .build();
//
//        // when
//        User user = userDomainMapper.fromUserJpaEntity(userJpaEntity);
//
//        // then
//        assertThat(user)
//                .extracting("email","name","pwd","userId","createdAt")
//                .contains("testEmail","testName",null,"testUser",date);
//    }
//
//    @Test
//    @DisplayName("RequestCreateUser 메서드로 [RequestCreateUser] -> [User] 변환에 성공한다.")
//    void fromRequestCreateUser() {
//        // given
//        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
//                .email("testEmail")
//                .pwd("testPwd")
//                .name("testName")
//                .build();
//
//        // when
//        User user = userDomainMapper.fromRequestCreateUser(requestCreateUser);
//
//        // then
//        assertThat(user)
//                .extracting("email","pwd","name")
//                .contains("testEmail","testPwd","testName");
//
//    }
//}