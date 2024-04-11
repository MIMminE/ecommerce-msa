package my.project.msa.user_service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import my.project.msa.user_service.domain_model.User;

import java.util.List;

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

    private List<ResponseOrder> orders;


    static public ResponseUser fromUserDomain(User user) {
        return ResponseUser.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .group(user.getGroup())
                .build();
    }

    static public ResponseUser fromUserDomainForRemove(User user){
        return ResponseUser.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .build();
    }
}