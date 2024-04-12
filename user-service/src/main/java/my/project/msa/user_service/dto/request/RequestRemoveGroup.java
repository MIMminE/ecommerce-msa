package my.project.msa.user_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import my.project.msa.user_service.domain_model.Group;

import static my.project.msa.user_service.exception.ExceptionHolder.REQUEST_VALID_GROUP_NAME_NOT_BLANK;
import static my.project.msa.user_service.exception.ExceptionHolder.REQUEST_VALID_SECRET_KEY_NOT_BLANK;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class RequestRemoveGroup {

    @NotBlank(message = REQUEST_VALID_GROUP_NAME_NOT_BLANK)
    private String groupName;
    @NotBlank(message = REQUEST_VALID_SECRET_KEY_NOT_BLANK)
    private String secretKey;

    public static Group toGroup(RequestRemoveGroup request){
        return Group.builder()
                .groupName(request.getGroupName())
                .secretKey(request.getSecretKey())
                .build();
    }
}
