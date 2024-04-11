package my.project.msa.user_service.persistent.jpa;

import my.project.msa.user_service.persistent.jpa.user.UserEntity;
import my.project.msa.user_service.persistent.jpa.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static my.project.msa.user_service.persistent.jpa.user.UserEntity.builder;

@SpringBootTest
@DisplayName("JPA 레파지토리 쿼리 메서드 테스트")
class UserJpaRepositoryTest {

    @Autowired
    UserRepository userJpaRepository;


    @DisplayName("findByUserId 메서드로 userId값을 이용한 조회를 성공한다.")
    @Test
    void findByUserId() {
        // given
        UserEntity userJpaEntity = builder()
                .email("testEmail")
                .name("testName")
                .userId("testUser")
                .encryptPwd("testPwd")
                .build();

        UserEntity savedEntity = userJpaRepository.save(userJpaEntity);

        // when
        UserEntity userEntity = userJpaRepository.findByUserId(savedEntity.getUserId()).orElseThrow();

        // then
        Assertions.assertThat(userEntity)
                .extracting("id", "email", "name", "userId", "encryptPwd")
                .contains(savedEntity.getId(), savedEntity.getEmail(), savedEntity.getName(), savedEntity.getUserId());
    }

}