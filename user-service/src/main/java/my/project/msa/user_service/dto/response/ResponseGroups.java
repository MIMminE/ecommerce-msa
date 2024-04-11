package my.project.msa.user_service.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseGroups {
    private Integer size;
    List<ResponseGroup> results;

    public ResponseGroups(List<ResponseGroup> results) {
        size = results.size();
        this.results = results;
    }
}
