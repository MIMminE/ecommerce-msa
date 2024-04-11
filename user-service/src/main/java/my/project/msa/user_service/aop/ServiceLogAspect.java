package my.project.msa.user_service.aop;

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
public class ServiceLogAspect {

    @Before("my.project.msa.user_service.aop.PointCuts.allUserService()")
    public void beforeAdvice(JoinPoint point) {
        log.info("Service Call : {} , params : {} ", point.getSignature().toShortString(), point.getArgs());
    }

    @AfterReturning(value = "my.project.msa.user_service.aop.PointCuts.allUserService()", returning = "result")
    public void afterAdvice(JoinPoint point, ResponseEntity<?> result) {
        log.info("Service {} return {}", point.getSignature().toShortString(), result.toString());
    }
}
