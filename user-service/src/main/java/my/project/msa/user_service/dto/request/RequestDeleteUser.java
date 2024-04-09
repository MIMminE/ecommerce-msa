package my.project.msa.user_service.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class RequestDeleteUser {
    String pwd;

    @Builder
    private RequestDeleteUser(String pwd) {
        this.pwd = pwd;
    }
}
