package my.project.msa.user_service.dto.response;

import lombok.*;
import my.project.msa.user_service.domain_model.Group;
import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.domain_model.vo.GroupAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ResponseGroup {

    private Long groupId;
    private String groupName;
    private GroupAuthority groupAuthority;
    private List<String> members;

    static public ResponseGroup fromGroup(Group group) {

        ResponseGroupBuilder builder = ResponseGroup.builder()
                .groupAuthority(group.getGroupAuthority())
                .groupId(group.getGroupId())
                .groupName(group.getGroupName());

        if (group.getMembers() != null){
            builder.members(group.getMembers().stream()
                    .map(User::getName)
                    .collect(Collectors.toList()));
        }
        return builder.build();
    }
}
