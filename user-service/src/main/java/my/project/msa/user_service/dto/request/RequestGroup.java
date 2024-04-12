package my.project.msa.user_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import my.project.msa.user_service.domain_model.Group;
import my.project.msa.user_service.domain_model.vo.GroupAuthority;

import java.util.ArrayList;

import static my.project.msa.user_service.exception.ExceptionHolder.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class RequestGroup {

    @NotBlank(message = REQUEST_VALID_GROUP_NAME_NOT_BLANK)
    @Size(min = 2, max = 16, message = REQUEST_VALID_GROUP_NAME_SIZE)
    private String groupName;

    @NotNull(message = REQUEST_VALID_GROUP_AUTHORITY_NOT_NULL)
    private GroupAuthority groupAuthority;

    @NotBlank(message = REQUEST_VALID_SECRET_KEY_NOT_BLANK)
    @Size(min = 8, max = 24, message = REQUEST_VALID_SECRET_KEY_SIZE)
    private String secretKey;

    public static Group toGroup(RequestGroup request) {
        return Group.builder()
                .groupName(request.getGroupName())
                .groupAuthority(request.getGroupAuthority())
                .secretKey(request.getSecretKey())
                .members(new ArrayList<>())
                .build();
    }
}
