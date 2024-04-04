package my.project.msa.user_service.persistent.jpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserJpaEntity {
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

    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @PrePersist
    protected void onCreate(){
        createdAt = new Date();
    }

    @Builder
    private UserJpaEntity(String email, String name, String userId, String encryptPwd, Date createdAt) {
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.encryptPwd = encryptPwd;
        this.createdAt = createdAt;
    }
}
