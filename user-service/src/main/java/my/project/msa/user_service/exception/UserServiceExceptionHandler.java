package my.project.msa.user_service.exception;

import my.project.msa.user_service.dto.request.RequestCreateUser;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static my.project.msa.user_service.dto.request.RequestCreateUser.*;

@RestControllerAdvice
public class UserServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) throws MethodArgumentNotValidException {
        if (e.getParameter().getParameterType().equals(RequestCreateUser.class)) {
            if (e.getMessage().contains(EMAIL_EX_NOTNULL_MESSAGE)) {
                return ResponseEntity.badRequest().body("유저 생성 요청에 대한 필수 정보(Email)가 누락되었습니다");
            }
            if (e.getMessage().contains(EMAIL_EX_FORMAT_MESSAGE)) {
                return ResponseEntity.badRequest().body("Email 포맷이 잘못되었습니다.");
            }
            if (e.getMessage().contains(PASSWORD_EX_NOTNULL_MESSAGE)) {
                return ResponseEntity.badRequest().body("유저 생성 요청에 대한 필수 정보(Password)가 누락되었습니다");
            }
            if (e.getMessage().contains(PASSWORD_EX_SIZE_MESSAGE)) {
                return ResponseEntity.badRequest().body("Password는 8 ~ 16 글자 사이여야 합니다.");
            }
            if (e.getMessage().contains(NAME_EX_NOTNULL_MESSAGE)) {
                return ResponseEntity.badRequest().body("유저 생성 요청에 대한 필수 정보(Name)가 누락되었습니다");
            }
            if (e.getMessage().contains(NAME_EX_SIZE_MESSAGE)) {
                return ResponseEntity.badRequest().body("Name은 2글자 이상이여야 합니다.");
            }

        }
        throw e;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityException(DataIntegrityViolationException e) {
        if (e.getRootCause().getMessage().contains("EMAIL NULLS FIRST")) {
            return ResponseEntity.badRequest().body("DB 입력 예외 -> 이미 등록된 이메일입니다.");
        }
        if (e.getRootCause().getMessage().contains("ENCRYPT_PWD NULLS FIRST")) {
            return ResponseEntity.badRequest().body("DB 입력 예외 -> 같은 패스워드를 사용하는 사용자가 이미 등록되어 있습니다.");
        }

        throw e;
    }
}
