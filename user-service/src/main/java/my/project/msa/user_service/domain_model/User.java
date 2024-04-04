package my.project.msa.user_service.domain_model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class User {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date createdAt;

    @Builder
    private User(String email, String name, String pwd, String userId, Date createdAt) {
        this.email = email;
        this.name = name;
        this.pwd = pwd;
        this.userId = userId;
        this.createdAt = createdAt;
    }
}
