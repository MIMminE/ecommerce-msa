package my.project.msa.user_service.controller;

import my.project.msa.user_service.dto.request.RequestCreateUser;
import my.project.msa.user_service.dto.response.ResponseUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

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


    @DisplayName("name, pwd, email 이 정상적으로 전달될 때 정상 응답이 반환된다.")
    @Test
    void createUserSuccess() {
        // given
        String url = createUrlToEndpoint("users");
        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
                .name("testname")
                .pwd("testpass")
                .email("test@example.com")
                .build();

        // when
        ResponseEntity<ResponseUser> result = restTemplate.postForEntity(url, requestCreateUser, ResponseUser.class);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    private static Stream<Arguments> provideEmailTestArgument() {
        return Stream.of(
                Arguments.of("badEmail", "Email 포맷이 잘못되었습니다."),
                Arguments.of(null, "유저 생성 요청에 대한 필수 정보(Email)가 누락되었습니다")
        );
    }


    @DisplayName("이메일을 입력하지 않았거나, 올바른 이메일 포맷이 아니면 예외 응답이 반환된다.")
    @ParameterizedTest
    @MethodSource("provideEmailTestArgument")
    void createUserBadEmail(String email, String exceptionMessage) {
        // given
        String url = createUrlToEndpoint("users");
        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
                .name("testname")
                .pwd("testpass")
                .email(email)
                .build();
        // when
        ResponseEntity<String> result = restTemplate.postForEntity(url, requestCreateUser, String.class);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getBody().toString()).isEqualTo(exceptionMessage);
    }


    private static Stream<Arguments> providePasswordTestArgument() {
        String count7Password = "abcdefd";
        String count17Password = "abcdefdeabcdefde1";

        return Stream.of(
                Arguments.of(count7Password, "Password는 8 ~ 16 글자 사이여야 합니다."),
                Arguments.of(count17Password, "Password는 8 ~ 16 글자 사이여야 합니다."),
                Arguments.of(null, "유저 생성 요청에 대한 필수 정보(Password)가 누락되었습니다")
        );
    }

    @DisplayName("패스워드를 입력하지 않았거나, 8 ~ 16 글자 사이가 아닌 패스워드를 입력하면 예외 응답이 반환된다.")
    @ParameterizedTest
    @MethodSource("providePasswordTestArgument")
    void createUserBadPassword(String password, String exceptionMessage) {
        // given
        String url = createUrlToEndpoint("users");
        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
                .name("testname")
                .pwd(password)
                .email("test@example.com")
                .build();
        // when
        ResponseEntity<String> result = restTemplate.postForEntity(url, requestCreateUser, String.class);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getBody().toString()).isEqualTo(exceptionMessage);
    }

    private static Stream<Arguments> provideNameTestArgument() {

        return Stream.of(
                Arguments.of("A", "Name은 2글자 이상이여야 합니다."),
                Arguments.of(null, "유저 생성 요청에 대한 필수 정보(Name)가 누락되었습니다")
        );
    }

    @DisplayName("이름을 입력하지 않았거나, 2글자보다 작은 이름을 입력하면 예외 응답이 반환된다.")
    @ParameterizedTest
    @MethodSource("provideNameTestArgument")
    void createUserBadName(String name, String exceptionMessage) {
        // given
        String url = createUrlToEndpoint("users");
        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
                .name(name)
                .pwd("testPassword")
                .email("test@example.com")
                .build();
        // when
        ResponseEntity<String> result = restTemplate.postForEntity(url, requestCreateUser, String.class);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getBody().toString()).isEqualTo(exceptionMessage);
    }


    private String createUrlToEndpoint(String endpoint) {
        return "http://localhost:" + port + "/user-service/" + endpoint;
    }
}