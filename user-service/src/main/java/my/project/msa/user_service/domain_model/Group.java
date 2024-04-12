package my.project.msa.user_service.domain_model;

import lombok.*;
import my.project.msa.user_service.domain_model.vo.GroupAuthority;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Group {
    private Long groupId;
    private String groupName;
    private GroupAuthority groupAuthority;
    private List<User> members;
    private String secretKey;
}
