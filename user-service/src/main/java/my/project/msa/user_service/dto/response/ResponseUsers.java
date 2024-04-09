package my.project.msa.user_service.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResponseUsers implements Serializable {
    int size;
    List<ResponseUser> result;

    public ResponseUsers(List<ResponseUser> responseUsers) {
        this.size = responseUsers.size();
        this.result = responseUsers;
    }
}
