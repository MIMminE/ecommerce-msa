package my.project.msa.order_service.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class OrderServiceExceptionHandler {

    final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e) throws MethodArgumentNotValidException {
        String exceptionMessageCode = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        if (exceptionMessageCode != null) {
            return ResponseEntity.badRequest().body(messageSource.getMessage(exceptionMessageCode, null, Locale.getDefault()));
        }

        throw e;
    }
}
