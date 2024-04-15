package my.project.msa.user_service.support;

import my.project.msa.user_service.dto.request.RequestCreateUser;
import my.project.msa.user_service.dto.request.RequestGroup;

import static my.project.msa.user_service.support.RestDocsHolder.RestDocsHolderType.*;
import static org.springframework.restdocs.payload.JsonFieldType.BOOLEAN;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;

@RestDocsHolder(request)
public abstract class RequestHolder {

    @RestDocsSnippet({
            @RestDocsField(name = "groupName", type = STRING, description = "그룹 이름12"),
            @RestDocsField(name = "groupAuthority.aquatic", type = BOOLEAN, description = "구매 권한11"),
            @RestDocsField(name = "groupAuthority.agricultural", type = BOOLEAN, description = "구매 권한12"),
            @RestDocsField(name = "groupAuthority.livestock", type = BOOLEAN, description = "구매 권한13"),
            @RestDocsField(name = "secretKey", type = STRING, description = "비밀키2")
    })
    public RequestGroup requestGroup;


    @RestDocsSnippet({
            @RestDocsField(name = "email", type = STRING, description = "이메일2"),
            @RestDocsField(name = "pwd", type = STRING, description = "패스워드2"),
            @RestDocsField(name = "name", type = STRING, description = "이름2"),
            @RestDocsField(name = "group", type = STRING, description = "그룹2"),
    })
    public RequestCreateUser requestCreateUser;

}
