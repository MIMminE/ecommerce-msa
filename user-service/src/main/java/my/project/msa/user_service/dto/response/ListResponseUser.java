package my.project.msa.user_service.dto.response;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
public class ListResponseUser implements Serializable {
    int size;
    final List<ResponseUser> responseUsers;

    public ListResponseUser(List<ResponseUser> responseUsers) {
        this.size = responseUsers.size();
        this.responseUsers = responseUsers;
    }
}
