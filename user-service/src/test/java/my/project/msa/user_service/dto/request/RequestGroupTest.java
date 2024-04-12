package my.project.msa.user_service.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import my.project.msa.user_service.domain_model.Group;
import my.project.msa.user_service.domain_model.vo.GroupAuthority;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static my.project.msa.user_service.exception.ExceptionHolder.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("RequestGroup Test")
class RequestGroupTest {

    static private final String GOOD_CASE_GROUP_NAME = "TestGroupName";
    static private final String GOOD_CASE_SECRET_KEY = "TestPassword";

    static private final String BAD_CASE_GROUP_NAME = "A";
    static private final String BAD_CASE_SECRET_KEY = "A";

    static private final GroupAuthority GOOD_CASE_GROUP_AUTHORITY =
            GroupAuthority.builder().livestock(true).aquatic(true).agricultural(false).build();

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @DisplayName("[RequestGroup] -> [Group] 변환 메서드 실행이 정상적으로 동작한다.")
    @Test
    void toGroup() {
        // given
        RequestGroup requestGroup = RequestGroup.builder()
                .groupName(GOOD_CASE_GROUP_NAME)
                .secretKey(GOOD_CASE_SECRET_KEY)
                .groupAuthority(GOOD_CASE_GROUP_AUTHORITY)
                .build();

        // when
        Group group = RequestGroup.toGroup(requestGroup);

        // then
        assertThat(group)
                .extracting("groupName","groupAuthority","secretKey")
                .contains(GOOD_CASE_GROUP_NAME, GOOD_CASE_GROUP_AUTHORITY, GOOD_CASE_SECRET_KEY);
        assertThat(group.getMembers()).isInstanceOf(ArrayList.class).hasSize(0);
    }

    @DisplayName("그룹 이름이 입력되지 않은 요청에 대한 예외를 발생시킨다.")
    @Test
    void validGroupNotBlank() {
        // given
        RequestGroup request = RequestGroup.builder()
                .secretKey(GOOD_CASE_SECRET_KEY)
                .groupAuthority(GOOD_CASE_GROUP_AUTHORITY)
                .build();

        // when
        Set<ConstraintViolation<RequestGroup>> violations = validator.validate(request);

        // then
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(REQUEST_VALID_GROUP_NAME_NOT_BLANK);
    }

    @DisplayName("그룹 이름의 길이가 범위를 벗어난 경우 예외를 발생시킨다.")
    @Test
    void validGroupSize() {
        // given
        RequestGroup request = RequestGroup.builder()
                .groupName(BAD_CASE_GROUP_NAME)
                .secretKey(GOOD_CASE_SECRET_KEY)
                .groupAuthority(GOOD_CASE_GROUP_AUTHORITY)
                .build();

        // when
        Set<ConstraintViolation<RequestGroup>> violations = validator.validate(request);

        // then
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(REQUEST_VALID_GROUP_NAME_SIZE);
    }

    @DisplayName("그룹 권한이 입력되지 않은 요청에 대한 예외를 발생시킨다.")
    @Test
    void validGroupAuthorityNotNull() {
        RequestGroup request = RequestGroup.builder()
                .groupName(GOOD_CASE_GROUP_NAME)
                .secretKey(GOOD_CASE_SECRET_KEY)
                .build();

        // when
        Set<ConstraintViolation<RequestGroup>> violations = validator.validate(request);

        // then
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(REQUEST_VALID_GROUP_AUTHORITY_NOT_NULL);
    }

    @DisplayName("그룹 비밀키가 입력되지 않은 요청에 대한 예외를 발생시킨다.")
    @Test
    void validGroupKeyNotBlank() {
        RequestGroup request = RequestGroup.builder()
                .groupName(GOOD_CASE_GROUP_NAME)
                .groupAuthority(GOOD_CASE_GROUP_AUTHORITY)
                .build();

        // when
        Set<ConstraintViolation<RequestGroup>> violations = validator.validate(request);

        // then
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(REQUEST_VALID_SECRET_KEY_NOT_BLANK);
    }

    @DisplayName("그룹 비밀키의 길이가 범위를 벗어난 경우 예외를 발생시킨다.")
    @Test
    void validGroupKeySize() {
        RequestGroup request = RequestGroup.builder()
                .groupName(GOOD_CASE_GROUP_NAME)
                .secretKey(BAD_CASE_SECRET_KEY)
                .groupAuthority(GOOD_CASE_GROUP_AUTHORITY)
                .build();

        // when
        Set<ConstraintViolation<RequestGroup>> violations = validator.validate(request);

        // then
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(REQUEST_VALID_SECRET_KEY_SIZE);
    }


}