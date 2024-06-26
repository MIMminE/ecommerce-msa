package my.project.msa.user_service.exception;

public abstract class ExceptionHolder {
    public static final String EMAIL_EX_NOTNULL_MESSAGE = "request_valid.email.not_null";
    public static final String EMAIL_EX_FORMAT_MESSAGE = "request_valid.email.bad_format";
    public static final String PASSWORD_EX_NOTNULL_MESSAGE = "request_valid.password.not_null";
    public static final String PASSWORD_EX_SIZE_MESSAGE = "request_valid.password.bad_size";
    public static final String NAME_EX_NOTNULL_MESSAGE = "request_valid.name.not_null";
    public static final String NAME_EX_SIZE_MESSAGE = "request_valid.name.bad_size";
    public static final String GROUP_EX_NOTNULL_MESSAGE = "request_valid.group.not_null";
    public static final String SERVICE_VALID_EX_FAIL_GROUP_FIND = "service_valid.group.not_found";
    public static final String SERVICE_VALID_EX_FAIL_USER_FIND = "service_valid.user.not_found";
    public static final String REQUEST_VALID_GROUP_NAME_NOT_BLANK = "request_valid.group_name.not_blank";
    public static final String REQUEST_VALID_GROUP_NAME_SIZE = "request_valid.group_name.size";
    public static final String REQUEST_VALID_GROUP_AUTHORITY_NOT_NULL = "request_valid.group_authority.not_null";
    public static final String REQUEST_VALID_SECRET_KEY_NOT_BLANK = "request_valid.secret_key.not_blank";
    public static final String REQUEST_VALID_SECRET_KEY_SIZE = "request_valid.secret_key.size";
}