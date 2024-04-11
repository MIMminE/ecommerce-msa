package my.project.msa.user_service.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseUsers implements Serializable {
    int size;
    List<ResponseUser> result;

    public ResponseUsers(List<ResponseUser> responseUsers) {
        this.size = responseUsers.size();
        this.result = responseUsers;
    }
}
