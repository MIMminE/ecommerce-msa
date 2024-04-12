package my.project.msa.user_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import my.project.msa.user_service.domain_model.Group;
import my.project.msa.user_service.dto.request.RequestCreateUser;
import my.project.msa.user_service.service.GroupService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class GroupControllerTest {

    private GroupService groupService = Mockito.mock(GroupService.class);
    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new GroupController(groupService)).build();
    private ObjectMapper mapper = new ObjectMapper();

    @DisplayName("/user-service/groups EndPoint 요청에 대한 결과값을 정상적으로 받는다.")
    @Test
    void getGroups() throws Exception {
        RequestCreateUser build = RequestCreateUser.builder()
                .name("Test31561")
                .group("groupA")
                .email("tests@example.com")
                .pwd("test2356412631")
                .build();

        // given
        given(groupService.createGroup(any(Group.class))).willReturn(
                Group.builder()
                        .groupName("Test")
                        .build()

        );
        mockMvc.perform(
                post("/group-service/groups")
                        .content(mapper.writeValueAsString(build))
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andDo(print());
    }
}