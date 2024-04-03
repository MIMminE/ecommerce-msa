package my.project.msa.user_service.domain_model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date createdAt;
}
