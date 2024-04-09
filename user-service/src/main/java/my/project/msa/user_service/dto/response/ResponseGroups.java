package my.project.msa.user_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseGroups {
    private Integer size;
    List<ResponseGroup> results;

    public ResponseGroups(List<ResponseGroup> results) {
        size = results.size();
        this.results = results;
    }
}
