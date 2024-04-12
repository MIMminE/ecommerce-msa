package my.project.msa.user_service.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import my.project.msa.user_service.domain_model.Group;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static my.project.msa.user_service.exception.ExceptionHolder.REQUEST_VALID_GROUP_NAME_NOT_BLANK;
import static my.project.msa.user_service.exception.ExceptionHolder.REQUEST_VALID_SECRET_KEY_NOT_BLANK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("RequestRemoveGroup Test")
class RequestRemoveGroupTest {

    static final private String GOOD_CASE_GROUP_NAME = "TestGroupName";
    static final private String GOOD_CASE_SECRET_KEY = "TestSecretKey";

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @DisplayName("[RequestRemoveGroup] -> [Group] 변환 메서드 실행이 정상적으로 동작한다.")
    @Test
    void toGroup() {
        // given
        RequestRemoveGroup request = RequestRemoveGroup.builder()
                .groupName(GOOD_CASE_GROUP_NAME)
                .secretKey(GOOD_CASE_SECRET_KEY)
                .build();

        // when
        Group group = RequestRemoveGroup.toGroup(request);

        // then
        assertThat(group)
                .extracting("groupName", "secretKey")
                .contains(GOOD_CASE_GROUP_NAME, GOOD_CASE_SECRET_KEY);
    }

    @DisplayName("그룹 이름이 입력되지 않은 요청에 대한 예외를 발생시킨다.")
    @Test
    void validGroupNameNotBlank() {
        // given
        RequestRemoveGroup request = RequestRemoveGroup.builder()
                .secretKey(GOOD_CASE_SECRET_KEY)
                .build();

        // when
        Set<ConstraintViolation<RequestRemoveGroup>> validations = validator.validate(request);

        // then
        assertThat(validations.iterator().next().getMessage())
                .isEqualTo(REQUEST_VALID_GROUP_NAME_NOT_BLANK);
    }

    @DisplayName("그룹 비밀키가 입력되지 않은 요청에 대한 예외를 발생시킨다.")
    @Test
    void validSecretKeyNotBlank() {
        // given
        RequestRemoveGroup request = RequestRemoveGroup.builder()
                .groupName(GOOD_CASE_GROUP_NAME)
                .build();

        // when
        Set<ConstraintViolation<RequestRemoveGroup>> validations = validator.validate(request);

        // then
        assertThat(validations.iterator().next().getMessage())
                .isEqualTo(REQUEST_VALID_SECRET_KEY_NOT_BLANK);
    }

}