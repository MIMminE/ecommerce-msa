package my.project.msa.user_service.dto.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ResponseUsers implements Serializable {
    int size;
    List<ResponseUser> result;

    public ResponseUsers(List<ResponseUser> responseUsers) {
        this.size = responseUsers.size();
        this.result = responseUsers;
    }
}
