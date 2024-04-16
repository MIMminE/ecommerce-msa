package my.project.msa.user_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.FixtureMonkeyBuilder;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import lombok.Data;
import lombok.Value;
import my.project.msa.user_service.domain_model.Group;
import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.domain_model.vo.GroupAuthority;
import my.project.msa.user_service.dto.request.RequestGroup;
import my.project.msa.user_service.dto.response.ResponseGroup;
import my.project.msa.user_service.service.GroupService;
import my.project.msa.user_service.support.RequestHolder;
import my.project.msa.user_service.support.ResponseHolder;
import nuts.restdocs.factory.RestDocsFactory;
import nuts.restdocs.factory.RestDocsSupport;
import nuts.restdocs.factory.impl.RestDocsSimpleFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;

import java.time.Instant;
import java.util.List;

import static com.navercorp.fixturemonkey.api.experimental.JavaGetterMethodPropertySelector.javaGetter;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GroupControllerTest extends RestDocsSupport {


    private final RestDocsFactory restDocsFactory = new RestDocsSimpleFactory(List.of(RequestHolder.class, ResponseHolder.class));
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
                .andDo(MockMvcRestDocumentation.document("test",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                restDocsFactory.requestFields(RequestGroup.class),
                                restDocsFactory.responseFields(ResponseGroup.class)
                        )
                );
    }

    @Value
    public class Order {
        Long id;

        String orderNo;

        String productName;

        int quantity;

        long price;

        List<String> items;

        Instant orderedAt;
    }

    @DisplayName("")
    @Test
    void test() {
        // given
        FixtureMonkey sut = FixtureMonkey.builder()
                .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
                .build();

        Order lineSally = sut.giveMeBuilder(Order.class)
                .set(javaGetter(Order::getOrderNo), "1")
                .set(javaGetter(Order::getProductName), "Line Sally")
                .minSize(javaGetter(Order::getItems), 1)
                .sample();


        String orderNo = lineSally.getOrderNo();
        System.out.println(orderNo);
        System.out.println(lineSally);
        // then

    }
}