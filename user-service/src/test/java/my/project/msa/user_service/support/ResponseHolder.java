package my.project.msa.user_service.support;

import my.project.msa.user_service.dto.response.ResponseGroup;

import static my.project.msa.user_service.support.RestDocsHolder.RestDocsHolderType.response;
import static org.springframework.restdocs.payload.JsonFieldType.*;

@RestDocsHolder(response)
public abstract class ResponseHolder {

    @RestDocsSnippet({
            @RestDocsField(name = "groupId", type = NUMBER, description = "그룹 Id"),
            @RestDocsField(name = "groupName", type = STRING, description = "그룹 이름"),
            @RestDocsField(name = "groupAuthority.aquatic", type = BOOLEAN, description = "그룹 권한 1"),
            @RestDocsField(name = "groupAuthority.agricultural", type = BOOLEAN, description = "그룹 권한 2"),
            @RestDocsField(name = "groupAuthority.livestock", type = BOOLEAN, description = "그룹 권한 3"),
            @RestDocsField(name = "members", type = ARRAY, description = "멤버 리스트")

    })
    public ResponseGroup responseGroup;

}
