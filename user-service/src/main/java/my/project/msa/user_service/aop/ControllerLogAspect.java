package my.project.msa.user_service.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerLogAspect {
    @Before("my.project.msa.user_service.aop.PointCuts.allUserController()")
    public void beforeAdvice(JoinPoint point) {
        log.info("Call : {} , params : {} ", point.getSignature(), point.getArgs());
    }
}
