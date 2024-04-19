package my.project.msa.user_service.domain_model;

import lombok.*;
import my.project.msa.user_service.domain_model.vo.GroupAuthority;
import nutslib.fixture.DefaultFixtureFactoryBuilder;
import nutslib.fixture.FixtureFactoryBuilderContext;
import nutslib.fixture.fieldconfig.vo.LanguageType;
import nutslib.fixture.inspector.ExpressionClassHolder;
import nutslib.fixture.inspector.FieldInfo;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Group {
    private Long groupId;
    private String groupName;
    private GroupAuthority groupAuthority;
    private List<User> members;
    private String secretKey;
    private List<LanguageType> languageType;
    private Map<String, User> users;
}
