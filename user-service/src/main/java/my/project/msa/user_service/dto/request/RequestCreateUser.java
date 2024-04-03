package my.project.msa.user_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestCreateUser {

    public static final String EMAIL_EX_NOTNULL_MESSAGE = "Email cannot be null";
    public static final String EMAIL_EX_SIZE_MESSAGE = "Email not be less than two characters";
    public static final String PASSWORD_EX_NOTNULL_MESSAGE = "Password cannot be null";
    public static final String PASSWORD_EX_SIZE_MESSAGE = "Password must be equal or grater than 8 characters and less 16 char";
    public static final String NAME_EX_NOTNULL_MESSAGE = "Name cannot be null";
    public static final String NAME_EX_SIZE_MESSAGE = "Name not be less than two characters";

    @NotNull(message = EMAIL_EX_NOTNULL_MESSAGE)
    @Size(min = 2, message = EMAIL_EX_SIZE_MESSAGE)
    @Email
    private String email;

    @NotNull(message = PASSWORD_EX_NOTNULL_MESSAGE)
    @Size(min = 8, max = 16, message = PASSWORD_EX_SIZE_MESSAGE)
    private String pwd;

    @NotNull(message = NAME_EX_NOTNULL_MESSAGE)
    @Size(min = 2, message = NAME_EX_SIZE_MESSAGE)
    private String name;

}
