package my.project.msa.user_service.dto.request;

import lombok.*;
import my.project.msa.user_service.domain_model.Group;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class RequestRemoveGroup {

    private String groupName;
    private String secretKey;

    public static Group toGroup(RequestRemoveGroup request){
        return Group.builder()
                .groupName(request.getGroupName())
                .secretKey(request.getSecretKey())
                .build();
    }
}
