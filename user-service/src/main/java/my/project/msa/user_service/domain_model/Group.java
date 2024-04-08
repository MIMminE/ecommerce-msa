package my.project.msa.user_service.domain_model;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Group {
    private String groupName;
    private String leaderName;
    private String leaderNumber;
    private List<String> members;

    @Builder
    private Group(String groupName, String leaderName, String leaderNumber, List<String> members) {
        this.groupName = groupName;
        this.leaderName = leaderName;
        this.leaderNumber = leaderNumber;
        this.members = members;
    }
}
