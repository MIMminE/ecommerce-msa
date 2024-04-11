package my.project.msa.user_service.persistent.jpa.user;

import jakarta.persistence.*;
import lombok.*;
import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.dto.request.RequestCreateUser;
import my.project.msa.user_service.persistent.CreatedBaseEntity;
import my.project.msa.user_service.persistent.jpa.group.GroupEntity;
import my.project.msa.user_service.util.PasswordEncoder;

@Entity
@Table(name = "users")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserEntity extends CreatedBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, unique = true)
    private String encryptPwd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    public static UserEntity fromRequestCreateUser(RequestCreateUser request) {
        return UserEntity.builder()
                .email(request.getEmail())
                .name(request.getName())
                .build();
    }

    public static User toUserDomain(UserEntity userEntity) {
        return User.builder()
                .userId(userEntity.getUserId())
                .pwd(null)
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .group(userEntity.getGroup().getGroupName())
                .build();
    }

    public static UserEntity fromUserDomain(User user) {
        return UserEntity.builder()
                .name(user.getName())
                .email(user.getEmail())
                .group(null)
                .encryptPwd(null)
                .build();
    }
}
