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
public class ControllerLogAspect {
    @Before("my.project.msa.user_service.aop.PointCuts.allUserController()")
    public void beforeAdvice(JoinPoint point) {
        log.info("Controller Call : {} , params : {} ", point.getSignature(), point.getArgs());
    }

    @AfterReturning(value = "my.project.msa.user_service.aop.PointCuts.allUserController()", returning = "responseEntity")
    public void afterAdvice(JoinPoint point, ResponseEntity<?> responseEntity){
        log.info("Controller {} return {}",point.getSignature().toShortString(), responseEntity.toString());
    }
}
