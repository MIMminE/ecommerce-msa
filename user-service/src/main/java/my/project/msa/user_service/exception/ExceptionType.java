package my.project.msa.user_service.exception;

public abstract class ExceptionType {
    public static final String EMAIL_EX_NOTNULL_MESSAGE = "request_valid.email.not_null";
    public static final String EMAIL_EX_FORMAT_MESSAGE = "request_valid.email.bad_format";
    public static final String PASSWORD_EX_NOTNULL_MESSAGE = "request_valid.password.not_null";
    public static final String PASSWORD_EX_SIZE_MESSAGE = "request_valid.password.bad_size";
    public static final String NAME_EX_NOTNULL_MESSAGE = "request_valid.name.not_null";
    public static final String NAME_EX_SIZE_MESSAGE = "request_valid.name.bad_size";
}