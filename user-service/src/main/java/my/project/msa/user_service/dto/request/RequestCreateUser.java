package my.project.msa.user_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.project.msa.user_service.exception.ExceptionType;

import static my.project.msa.user_service.exception.ExceptionType.*;

@Data
@NoArgsConstructor
public class RequestCreateUser {

    @NotNull(message = EMAIL_EX_NOTNULL_MESSAGE )
    @Email(message = EMAIL_EX_FORMAT_MESSAGE)
    private String email;

    @NotNull(message = PASSWORD_EX_NOTNULL_MESSAGE)
    @Size(min = 8, max = 16, message = PASSWORD_EX_SIZE_MESSAGE)
    private String pwd;

    @NotNull(message = NAME_EX_NOTNULL_MESSAGE)
    @Size(min = 2, message = NAME_EX_SIZE_MESSAGE)
    private String name;

    @Builder
    private RequestCreateUser(String email, String pwd, String name) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
    }
}
