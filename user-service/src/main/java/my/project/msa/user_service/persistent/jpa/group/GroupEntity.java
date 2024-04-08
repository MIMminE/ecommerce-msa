package my.project.msa.user_service.persistent.jpa.group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import my.project.msa.user_service.persistent.CreatedBaseEntity;
import my.project.msa.user_service.persistent.jpa.user.UserEntity;
import my.project.msa.user_service.vo.DepartmentLeaderInfo;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class GroupEntity extends CreatedBaseEntity {

    @Id @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    private String groupName;

    @Embedded
    private DepartmentLeaderInfo leaderInfo;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "group")
    private List<UserEntity> members = new ArrayList<>();

    @Builder
    private GroupEntity(String groupName, String leaderName, String leaderNumber, List<UserEntity> members) {
        this.groupName = groupName;
        this.leaderInfo = DepartmentLeaderInfo.builder()
                .leaderName(leaderName)
                .leaderNumber(leaderNumber)
                .build();
        this.members = members;
    }
}
