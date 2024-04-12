package my.project.msa.user_service.dto.response;

import my.project.msa.user_service.domain_model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseUserTest {

    static final private String USER_NAME = "TestUserName";
    static final private String USER_ID = "TestUserId";
    static final private String PWD = "TestPWD";
    static final private String EMAIL = "Test@example.com";
    static final private String GROUP = "TestGroup";


    @DisplayName("[User] -> [ResponseUser] 변환 메서드 실행이 정상적으로 동작한다.")
    @Test
    void fromUserDomain() {
        // given
        User user = User.builder()
                .name(USER_NAME)
                .userId(USER_ID)
                .pwd(PWD)
                .email(EMAIL)
                .group(GROUP)
                .build();

        // when
        ResponseUser responseUser = ResponseUser.fromUserDomain(user);

        // then
        Assertions.assertThat(responseUser)
                .extracting("userId","name","email","group")
                .contains(USER_ID, USER_NAME, EMAIL, GROUP);
    }
}