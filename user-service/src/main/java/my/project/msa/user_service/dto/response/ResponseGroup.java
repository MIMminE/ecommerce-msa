package my.project.msa.user_service.dto.response;

import lombok.*;
import my.project.msa.user_service.domain_model.Group;
import my.project.msa.user_service.domain_model.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ResponseGroup {

    private String groupId;
    private String groupName;
    private List<String> members;

    static public ResponseGroup fromGroup(Group group) {
        return ResponseGroup.builder()
                .groupName(group.getGroupName())
                .members(group.getMembers().stream()
                        .map(User::getName)
                        .collect(Collectors.toList()))
                .build();
    }
}
