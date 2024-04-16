package my.project.msa.user_service.support;

import my.project.msa.user_service.dto.response.ResponseGroup;
import nuts.restdocs.factory.RestDocsField;
import nuts.restdocs.factory.RestDocsHolder;
import nuts.restdocs.factory.RestDocsSnippet;

import static org.springframework.restdocs.payload.JsonFieldType.*;

@RestDocsHolder(RestDocsHolder.RestDocsHolderType.response)
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
