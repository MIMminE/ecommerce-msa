package my.project.msa.order_service.exception;

import my.project.msa.order_service.dto.request.RequestCreateOrder;
import my.project.msa.order_service.dto.request.RequestModifyOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static my.project.msa.order_service.dto.request.RequestModifyOrder.*;

@RestControllerAdvice
public class OrderServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e) throws MethodArgumentNotValidException {

        if (e.getParameter().getParameterType().equals(RequestModifyOrder.class)) {
            if (e.getMessage().contains(QTY_EX_NOTNULL_MESSAGE)) {
                return ResponseEntity.badRequest().body("주문 수정 요청에 대한 필수 정보(QTY)가 누락되었습니다.");
            }
        }

        if (e.getParameter().getParameterType().equals(RequestCreateOrder.class)) {
            if (e.getMessage().contains(RequestCreateOrder.PRODUCT_ID_EX_NOTNULL_MESSAGE)) {
                return ResponseEntity.badRequest().body("주문 생성 요청에 대한 필수 정보(Product ID)가 누락되었습니다.");
            }

            if (e.getMessage().contains(RequestCreateOrder.QTY_EX_NOTNULL_MESSAGE)) {
                return ResponseEntity.badRequest().body("주문 생성 요청에 대한 필수 정보(QTY)가 누락되었습니다.");
            }
            if (e.getMessage().contains(RequestCreateOrder.UNIT_PRICE_EX_NOTNULL_MESSAGE)) {
                return ResponseEntity.badRequest().body("주문 생성 요청에 대한 필수 정보(Unit Price)가 누락되었습니다.");
            }
        }
        throw e;

    }
}
