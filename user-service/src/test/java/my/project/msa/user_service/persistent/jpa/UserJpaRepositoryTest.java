package my.project.msa.user_service.persistent.jpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;

import static my.project.msa.user_service.persistent.jpa.UserJpaEntity.builder;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("JPA 레파지토리 쿼리 메서드 테스트")
class UserJpaRepositoryTest {

    @Autowired
    UserJpaRepository userJpaRepository;


    @DisplayName("findByUserId 메서드로 userId값을 이용한 조회를 성공한다.")
    @Test
    void findByUserId() {
        // given
        UserJpaEntity userJpaEntity = builder()
                .email("testEmail")
                .name("testName")
                .userId("testUser")
                .encryptPwd("testPwd")
                .build();

        UserJpaEntity savedEntity = userJpaRepository.save(userJpaEntity);

        // when
        UserJpaEntity byUserId = userJpaRepository.findByUserId(savedEntity.getUserId());

        // then
        Assertions.assertThat(byUserId)
                .extracting("id", "email", "name", "userId", "encryptPwd")
                .contains(savedEntity.getId(), savedEntity.getEmail(), savedEntity.getName(), savedEntity.getUserId());
    }

}