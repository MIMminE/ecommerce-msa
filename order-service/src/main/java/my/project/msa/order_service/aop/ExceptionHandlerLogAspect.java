package my.project.msa.order_service.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExceptionHandlerLogAspect {

    @Before("my.project.msa.order_service.aop.PointCuts.allExceptionHandler()")
    void beforeAdvice(JoinPoint point){
        log.info("[ExceptionHandler] call -> {},Exception : {}"
        , point.getSignature().toShortString(), point.getArgs().toString());
    }

    @AfterReturning(value = "my.project.msa.order_service.aop.PointCuts.allExceptionHandler()", returning = "result")
    void afterReturnAdvice(ResponseEntity<?> result){
        log.info("[ExceptionHandler] return -> {}", result.toString());
    }

}
