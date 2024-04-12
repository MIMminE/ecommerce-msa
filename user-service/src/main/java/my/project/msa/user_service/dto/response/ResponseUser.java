package my.project.msa.user_service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import my.project.msa.user_service.domain_model.User;

import java.util.List;
import java.util.Optional;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ResponseUser {
    private String userId;
    private String name;
    private String email;
    private String group;

    private List<ResponseOrder> orders; // TODO

    static public ResponseUser fromUserDomain(User user) {

        ResponseUserBuilder builder = ResponseUser.builder();

        if (user.getUserId() != null)
            builder.userId(user.getUserId());

        if (user.getUserId() != null)
            builder.name(user.getName());

        if (user.getEmail() != null) {
            builder.email(user.getEmail());
        }
        if (user.getGroup() != null) {
            builder.group(user.getGroup());
        }

        return builder.build();
    }

    static public ResponseUser fromUserDomainForRemove(User user) {
        return ResponseUser.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .build();
    }
}