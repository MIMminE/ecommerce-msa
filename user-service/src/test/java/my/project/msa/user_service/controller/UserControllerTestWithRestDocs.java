package my.project.msa.user_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.dto.request.RequestCreateUser;
import my.project.msa.user_service.service.UserService;
import my.project.msa.user_service.support.RequestHolder;
import my.project.msa.user_service.support.ResponseHolder;
import nuts.restdocs.factory.RestDocsFactory;
import nuts.restdocs.factory.RestDocsSupport;
import nuts.restdocs.factory.impl.RestDocsSimpleFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTestWithRestDocs extends RestDocsSupport {

    private final RestDocsFactory restDocsFactory = new RestDocsSimpleFactory(List.of(RequestHolder.class, ResponseHolder.class));
    UserService userService = Mockito.mock(UserService.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected Object initController() {
        return new UserController(userService);
    }

    @DisplayName("RequestCreateUser")
    @Test
    void test() throws Exception {
        // given

        RequestCreateUser requestCreateUser = RequestCreateUser.builder()
                .email("test@example.com")
                .name("testName")
                .pwd("testPassword")
                .group("TestGroup")
                .build();

        User user = User.builder()
                .userId("testId")
                .name("testName")
                .email("test@example.com")
                .group("TestGroup")
                .build();


        BDDMockito.given(userService.createUser(requestCreateUser)).willReturn(user);

        mockMvc.perform(post(
                        "/user-service/users")
                        .content(mapper.writeValueAsString(requestCreateUser))
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andDo(restDocsFactory.document("test", RequestCreateUser.class, null));

        // when

        // then
    }
}
