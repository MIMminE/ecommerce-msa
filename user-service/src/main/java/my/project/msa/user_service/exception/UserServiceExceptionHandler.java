package my.project.msa.user_service.exception;

import lombok.RequiredArgsConstructor;
import my.project.msa.user_service.dto.request.RequestCreateUser;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;
import java.util.Objects;

@RestControllerAdvice
@RequiredArgsConstructor
public class UserServiceExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(ServiceValidException.class)
    public ResponseEntity<String> handleServiceValidException(ServiceValidException e) {
        String ExceptionMessageCode = e.getMessage();

        return ResponseEntity.badRequest().body(
                messageSource.getMessage(Objects.requireNonNull(ExceptionMessageCode), null, Locale.getDefault()));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) throws MethodArgumentNotValidException {
        if (e.getParameter().getParameterType().equals(RequestCreateUser.class)) {
            String ExceptionMessageCode = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(
                    messageSource.getMessage(Objects.requireNonNull(ExceptionMessageCode), null, Locale.getDefault()));
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

    @ExceptionHandler(Exception.class)
    public void Ex(Exception e) {
        e.printStackTrace();
    }
}
