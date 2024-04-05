package my.project.msa.user_service.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static my.project.msa.user_service.exception.ExceptionHolder.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("유저 생성 요청 Request 바인딩 예외 테스트")
class RequestCreateUserTest {

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @DisplayName("Email 값이 누락되었을 때 정상적으로 예외가 발생한다.")
    @Test
    void validateByEmailNotnull() {
        // given
        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
                .name("testName")
                .pwd("testPwd123")
                .build();

        // when
        Set<ConstraintViolation<RequestCreateUser>> violations = validator.validate(requestCreateUser);
        System.out.println(violations);
        // then
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(EMAIL_EX_NOTNULL_MESSAGE);
    }

    @DisplayName("Email 포맷이 올바르지 않으면 정상적으로 예외가 발생한다.")
    @Test
    void validateByEmailSize() {
        // given
        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
                .email("U")
                .name("testName")
                .pwd("testPwdgf")
                .build();

        // when
        Set<ConstraintViolation<RequestCreateUser>> violations = validator.validate(requestCreateUser);

        // then
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(EMAIL_EX_FORMAT_MESSAGE);
    }

    @DisplayName("Pwd가 입력되지 않으면 정상적으로 예외가 발생한다.")
    @Test
    void validateByPwdNotNull() {
        // given
        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
                .email("testEmail@naver.com")
                .name("testName")
                .build();

        // when
        Set<ConstraintViolation<RequestCreateUser>> violations = validator.validate(requestCreateUser);

        // then
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(PASSWORD_EX_NOTNULL_MESSAGE);
    }

    @DisplayName("Pwd가 길이가 8보다 작거나 16글자보다 길면 정상적으로 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"test123","test1234test12345"})
    void validateByPwdSize(String pwd) {
        // given
        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
                .email("testEmail@naver.com")
                .pwd(pwd)
                .name("testName")
                .build();

        // when
        Set<ConstraintViolation<RequestCreateUser>> violations = validator.validate(requestCreateUser);

        // then
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(PASSWORD_EX_SIZE_MESSAGE);
    }

    @DisplayName("Name이 입력되지 않으면 정상적으로 예외가 발생한다.")
    @Test
    void validateByNameNotNull() {
        // given
        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
                .email("testEmail@naver.com")
                .pwd("test1234")
                .build();

        // when
        Set<ConstraintViolation<RequestCreateUser>> violations = validator.validate(requestCreateUser);

        // then
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(NAME_EX_NOTNULL_MESSAGE);
    }

    @DisplayName("Name이 길이가 2글자보다 작으면 정상적으로 예외가 발생한다.")
    @Test
    void validateByNameSize() {
        // given
        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
                .email("testEmail@naver.com")
                .pwd("test1234")
                .name("A")
                .build();

        // when
        Set<ConstraintViolation<RequestCreateUser>> violations = validator.validate(requestCreateUser);

        // then
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(NAME_EX_SIZE_MESSAGE);
    }
}