package my.project.msa.user_service.domain_model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    private String userId;
    private String pwd;
    private String name;
    private String email;
    private String group;
}
