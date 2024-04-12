package my.project.msa.user_service.dto.response;

import my.project.msa.user_service.domain_model.Group;
import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.domain_model.vo.GroupAuthority;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResponseGroupTest {

    static final private Long GROUP_ID = 1L;
    static final private String GROUP_NAME = "TestGroupName";
    static final private GroupAuthority GROUP_AUTHORITY = GroupAuthority.builder()
                                                            .agricultural(true).aquatic(true).livestock(false).build();

    final private List<User> group_members = providerGroupMembers();

    private List<User> providerGroupMembers(){
        User user1 = User.builder().name("member1").build();
        User user2 = User.builder().name("member2").build();
        User user3 = User.builder().name("member3").build();
        User user4 = User.builder().name("member4").build();

        return List.of(user1, user2, user3, user4);
    }

    @DisplayName("[Group] -> [ResponseGroup] 변환 메서드 실행이 정살적으로 동작한다.")
    @Test
    void fromGroup() {
        // given
        Group group = Group.builder()
                .groupId(GROUP_ID)
                .groupName(GROUP_NAME)
                .groupAuthority(GROUP_AUTHORITY)
                .members(group_members)
                .build();

        // when
        ResponseGroup responseGroup = ResponseGroup.fromGroup(group);

        // then
        assertThat(responseGroup)
                .extracting("groupId","groupName","groupAuthority","members")
                .contains(GROUP_ID, GROUP_NAME, GROUP_AUTHORITY, List.of("member1","member2","member3","member4"));
    }

    @DisplayName("[Group] -> [ResponseGroup] 변환 메서드 실행이 그룹에 속한 멤버가 없을때 정살적으로 동작한다.")
    @Test
    void fromGroupNoMembers() {
        // given
        Group group = Group.builder()
                .groupId(GROUP_ID)
                .groupName(GROUP_NAME)
                .groupAuthority(GROUP_AUTHORITY)
                .build();

        // when
        ResponseGroup responseGroup = ResponseGroup.fromGroup(group);

        // then
        assertThat(responseGroup)
                .extracting("groupId","groupName","groupAuthority","members")
                .contains(GROUP_ID, GROUP_NAME, GROUP_AUTHORITY, null);
    }
}