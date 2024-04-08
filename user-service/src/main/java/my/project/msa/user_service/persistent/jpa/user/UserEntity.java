package my.project.msa.user_service.persistent.jpa.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import my.project.msa.user_service.dto.request.RequestCreateUser;
import my.project.msa.user_service.persistent.CreatedBaseEntity;
import my.project.msa.user_service.persistent.jpa.group.GroupEntity;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserEntity extends CreatedBaseEntity {
    public static final String ENCRYPT_PWD_NUT_NULL = "encryptPwd가 비어있습니다.";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String userId;

    @NotNull(message = ENCRYPT_PWD_NUT_NULL)
    @Column(nullable = false, unique = true)
    private String encryptPwd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GroupEntity group;


    @Builder
    private UserEntity(String email, String name, String userId, String encryptPwd, GroupEntity group) {
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.encryptPwd = encryptPwd;
        if (group != null) {
            setGroup(group);
        }
    }

    public void setGroup(GroupEntity groupEntity) {
        this.group = groupEntity;
        groupEntity.getMembers().add(this);
    }

    public static UserEntity fromRequestCreateUser(RequestCreateUser request) {
        return UserEntity.builder()
                .email(request.getEmail())
                .name(request.getName())
                .build();
    }
}
