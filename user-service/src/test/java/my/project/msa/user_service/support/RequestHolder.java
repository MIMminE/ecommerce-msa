package my.project.msa.user_service.support;

import my.project.msa.user_service.dto.request.RequestCreateUser;
import my.project.msa.user_service.dto.request.RequestGroup;
import nuts.restdocs.factory.RestDocsField;
import nuts.restdocs.factory.RestDocsHolder;
import nuts.restdocs.factory.RestDocsSnippet;

import static org.springframework.restdocs.payload.JsonFieldType.BOOLEAN;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;

@RestDocsHolder
public abstract class RequestHolder {

    @RestDocsSnippet({
            @RestDocsField(name = "groupName", type = STRING, description = "그룹 이름2"),
            @RestDocsField(name = "groupAuthority.aquatic", type = BOOLEAN, description = "구매 권한1"),
            @RestDocsField(name = "groupAuthority.agricultural", type = BOOLEAN, description = "구매 권한2"),
            @RestDocsField(name = "groupAuthority.livestock", type = BOOLEAN, description = "구매 권한3"),
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
