package my.project.msa.user_service.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ResponseGroup {
    @Setter
    private String groupId;
    private final String groupName;
    private final List<String> members;
}
