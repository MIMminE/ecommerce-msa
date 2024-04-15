package my.project.msa.user_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import my.project.msa.user_service.domain_model.Group;
import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.domain_model.vo.GroupAuthority;
import my.project.msa.user_service.dto.request.RequestGroup;
import my.project.msa.user_service.dto.response.ResponseGroup;
import my.project.msa.user_service.service.GroupService;
import my.project.msa.user_service.support.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GroupControllerTest extends RestDocsSupport {

    private final GroupService groupService = Mockito.mock(GroupService.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected Object initController() {
        return new GroupController(groupService);
    }

    @DisplayName("/user-service/groups EndPoint 요청에 대한 결과값을 정상적으로 받는다.")
    @Test
    void getGroups() throws Exception {

        RequestGroup requestGroup = RequestGroup.builder()
                .groupName("TestGroup")
                .groupAuthority(GroupAuthority.builder()
                        .livestock(true).aquatic(true).agricultural(true)
                        .build())
                .secretKey("TestSecretKey")
                .build();


        // given
        given(groupService.createGroup(any(Group.class))).willReturn(
                Group.builder()
                        .groupId(15L)
                        .groupName("Test")
                        .groupAuthority(GroupAuthority.builder()
                                .agricultural(true)
                                .aquatic(true)
                                .livestock(false)
                                .build())
                        .members(List.of(User.builder()
                                .name("testUser1")
                                .userId("testUserId1")
                                .build(), User.builder()
                                .name("testUser2")
                                .userId("testUserId2")
                                .build()))
                        .build());

        mockMvc.perform(
                        post("/group-service/groups")
                                .content(mapper.writeValueAsString(requestGroup))
                                .characterEncoding("utf-8")
                                .contentType(MediaType.APPLICATION_JSON)

                ).andExpect(status().isCreated())
                .andDo(print())
                .andDo(restDocsFactory.document("groupTestApiDocs", RequestGroup.class, ResponseGroup.class));
    }
}