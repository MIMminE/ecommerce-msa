package my.project.msa.user_service.domain_model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Group group;
}
