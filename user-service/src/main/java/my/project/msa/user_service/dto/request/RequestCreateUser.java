package my.project.msa.user_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import static my.project.msa.user_service.exception.ExceptionHolder.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
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

    @NotNull(message = GROUP_EX_NOTNULL_MESSAGE)
    private String group;
}
