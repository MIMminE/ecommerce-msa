package my.project.msa.user_service.controller;

import my.project.msa.user_service.dto.request.RequestCreateUser;
import my.project.msa.user_service.dto.request.RequestDeleteUser;
import my.project.msa.user_service.dto.response.ResponseUser;
import my.project.msa.user_service.dto.response.ResponseUsers;
import my.project.msa.user_service.test_support.ControllerTestSupport;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UserControllerTest extends ControllerTestSupport {


    @DisplayName("/health_check 엔드포인트에 요청을 보내고 결과를 반환받는다.")
    @Test
    void status() {
        // given
        String url = createUrlToEndpoint("health_check");

        // when
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);

        // then
        assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @DisplayName("/users EndPoint 에 대한 요청이 정상적으로 수행된다.")
    @Test
    @Transactional
    void createUserSuccess() {

        // given
        String url = createUrlToEndpoint("users");
        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
                .name("testname")
                .pwd("testpass")
                .email("test1@example.com")
                .group("groupA")
                .build();

        // when
        ResponseEntity<ResponseUser> result = restTemplate.postForEntity(url, requestCreateUser, ResponseUser.class);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    private static Stream<Arguments> provideEmailTestArgument() {
        String badFormatMessageCode = "request_valid.email.bad_format";
        String notNullMessageCode = "request_valid.email.not_null";

        return Stream.of(
                Arguments.of("badEmail", badFormatMessageCode),
                Arguments.of(null, notNullMessageCode)
        );
    }

    @DisplayName("/users EndPoint 에 대한 요청에 대한 예외를 정상적으로 받는다.")
    @ParameterizedTest
    @MethodSource("provideEmailTestArgument")
    void createUserBadEmail(String email, String exceptionMessageCode) {
        // given
        String url = createUrlToEndpoint("users");
        String expected = messageSource.getMessage(exceptionMessageCode, null, Locale.getDefault());
        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
                .name("testname")
                .pwd("testpass")
                .email(email)
                .group("groupA")
                .build();
        // when
        ResponseEntity<String> result = restTemplate.postForEntity(url, requestCreateUser, String.class);
        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getBody().toString()).isEqualTo(expected);


    }


    private static Stream<Arguments> providePasswordTestArgument() {
        String count7Password = "abcdefd";
        String count17Password = "abcdefdeabcdefde1";

        String badSizeMessageCode = "request_valid.password.bad_size";
        String notNUllMessageCode = "request_valid.password.not_null";

        return Stream.of(
                Arguments.of(count7Password, badSizeMessageCode),
                Arguments.of(count17Password, badSizeMessageCode),
                Arguments.of(null, notNUllMessageCode)
        );
    }

    @DisplayName("패스워드를 입력하지 않았거나, 8 ~ 16 글자 사이가 아닌 패스워드를 입력하면 예외 응답이 반환된다.")
    @ParameterizedTest
    @MethodSource("providePasswordTestArgument")
    void createUserBadPassword(String password, String exceptionMessageCode) {
        // given
        String url = createUrlToEndpoint("users");
        String expected = messageSource.getMessage(exceptionMessageCode, null, Locale.getDefault());
        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
                .name("testname")
                .pwd(password)
                .email("test2@example.com")
                .group("groupA")
                .build();
        // when
        ResponseEntity<String> result = restTemplate.postForEntity(url, requestCreateUser, String.class);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getBody().toString()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideNameTestArgument() {

        String notNullMessageCode = "request_valid.name.not_null";
        String badSizeMessageCode = "request_valid.name.bad_size";

        return Stream.of(
                Arguments.of("A", badSizeMessageCode),
                Arguments.of(null, notNullMessageCode)
        );
    }

    @DisplayName("이름을 입력하지 않았거나, 2글자보다 작은 이름을 입력하면 예외 응답이 반환된다.")
    @ParameterizedTest
    @MethodSource("provideNameTestArgument")
    void createUserBadName(String name, String exceptionMessageCode) {
        // given
        String url = createUrlToEndpoint("users");
        String expected = messageSource.getMessage(exceptionMessageCode, null, Locale.getDefault());
        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
                .name(name)
                .pwd("testPassword")
                .email("test3@example.com")
                .group("groupA")
                .build();
        // when
        ResponseEntity<String> result = restTemplate.postForEntity(url, requestCreateUser, String.class);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getBody().toString()).isEqualTo(expected);
    }

    @DisplayName("/users/{userId}/remove Endpoint 요청을 보내면 userId와 매칭되는 유저 정보를 삭제에 성공한다.")
    @Test
    @Transactional
    void deleteUserSuccess() {

        // given
        // 서버에 유저 정보 등록
        String testName = "testName";
        String testPassword = "testPassword";
        String testEmail = "test4@example.com";
        String groupName = "groupA";

        String createUrl = createUrlToEndpoint("users");
        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
                .name(testName)
                .pwd(testPassword)
                .email(testEmail)
                .group(groupName)
                .build();

        ResponseEntity<ResponseUser> createdResponse =
                restTemplate.postForEntity(createUrl, requestCreateUser, ResponseUser.class);

        String userId = Objects.requireNonNull(createdResponse.getBody()).getUserId();

        String url = createUrlToEndpoint(String.format("users/%s/remove", userId));
        RequestDeleteUser request = RequestDeleteUser.builder().pwd(testPassword).build();

        // when
        ResponseEntity<ResponseUser> result = restTemplate.postForEntity(url, request, ResponseUser.class);

        // then
        assertThat(result.getBody()).extracting("userId", "name").contains(userId, testName);
    }

    @DisplayName("/users/{userId}/remove Endpoint 요청에 매칭되는 유저 정보가 없다면 예외를 반환한다.")
    @Test
    @Transactional
    void deleteUserFail() {
        // given
        // 서버에 유저 정보 등록
        String testName = "testName";
        String testPassword = "testPassword1";
        String testEmail = "test5@example.com";
        String groupName = "groupA";

        String createUrl = createUrlToEndpoint("users");
        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
                .name(testName)
                .pwd(testPassword)
                .email(testEmail)
                .group(groupName)
                .build();

        ResponseEntity<ResponseUser> createdResponse =
                restTemplate.postForEntity(createUrl, requestCreateUser, ResponseUser.class);

        String userId = Objects.requireNonNull(createdResponse.getBody()).getUserId();

        String url = createUrlToEndpoint(String.format("users/%s/remove", userId));
        RequestDeleteUser request = RequestDeleteUser.builder().pwd("failPassword").build();

        // when
        ResponseEntity<String> result = restTemplate.postForEntity(url, request, String.class);

        // then
        assertThat(result.getBody()).isEqualTo("유저 정보를 찾을 수 없습니다.");
    }


    @DisplayName("GET /users Endpoint 요청을 보내면 등록되어 있는 유저 정보를 반환한다.")
    @Test
    @Transactional
    void getUsers() {

        // given
        // 서버에 유저 정보 등록
        String testName = "testName";
        String testPassword = "testPassword";
        String testEmail = "test6@example.com";
        String groupName = "groupA";

        ResponseEntity<ResponseUser> createdResponse =
                restTemplate.postForEntity(createUrlToEndpoint("users"), RequestCreateUser.builder()
                        .name(testName)
                        .pwd(testPassword)
                        .email(testEmail)
                        .group(groupName)
                        .build(), ResponseUser.class);

        String testName2 = "testName";
        String testPassword2 = "testPassword2";
        String testEmail2 = "test2@example.com";
        String groupName2 = "groupA";

        ResponseEntity<ResponseUser> createdResponse2 =
                restTemplate.postForEntity(createUrlToEndpoint("users"), RequestCreateUser.builder()
                        .name(testName2)
                        .pwd(testPassword2)
                        .email(testEmail2)
                        .group(groupName2)
                        .build(), ResponseUser.class);

        // when
        String url = createUrlToEndpoint("users");
        ResponseEntity<ResponseUsers> result = restTemplate.getForEntity(url, ResponseUsers.class);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getResult())
                .extracting("userId", "name", "email", "group")
                .contains(
                        Tuple.tuple(Objects.requireNonNull(createdResponse.getBody()).getUserId(),
                                testName,
                                testEmail,
                                groupName),
                        Tuple.tuple(Objects.requireNonNull(createdResponse2.getBody()).getUserId(),
                                testName2,
                                testEmail2,
                                groupName2)

                );
    }

    @DisplayName("/users/{userId} Endpoint 요청을 보내면 userId와 매칭되는 유저 정보를 반환한다.")
    @Test
    void getUser() {
        String url = createUrlToEndpoint("health_check");

        // when
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);

        // then
        assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}