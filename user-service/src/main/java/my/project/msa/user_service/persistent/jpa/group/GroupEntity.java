package my.project.msa.user_service.persistent.jpa.group;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import my.project.msa.user_service.domain_model.Group;
import my.project.msa.user_service.persistent.CreatedBaseEntity;
import my.project.msa.user_service.persistent.jpa.user.UserEntity;
import my.project.msa.user_service.domain_model.vo.GroupAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class GroupEntity extends CreatedBaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    @NotNull
    private String groupName;

    @Embedded
    @NotNull
    private GroupAuthority groupAuthority;

    @ToString.Exclude
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEntity> members = new ArrayList<>();

    @Builder
    private GroupEntity(String groupName, GroupAuthority groupAuthority, List<UserEntity> members) {
        this.groupName = groupName;
        this.groupAuthority = groupAuthority;
        this.members = members;
    }

    // 연관관계 편의 메서드
    public void addUser(UserEntity user) {
        user.setGroup(this);
        this.members.add(user);
    }

    public void removeUser(UserEntity user) {
        this.members.remove(user);
        user.setGroup(null);
    }

    public static Group toGroupDomain(GroupEntity groupEntity) {
        return Group.builder()
                .groupName(groupEntity.groupName)
                .groupAuthority(groupEntity.groupAuthority)
                .members(groupEntity.getMembers().stream()
                        .map(UserEntity::toUserDomain)
                        .toList())
                .build();
    }
}
