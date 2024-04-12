package my.project.msa.user_service.dto.response;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ResponseGroups {
    private Integer size;
    List<ResponseGroup> results;

    public ResponseGroups(List<ResponseGroup> results) {
        size = results.size();
        this.results = results;
    }
}
