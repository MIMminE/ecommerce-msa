package my.project.msa.order_service.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ControllerLogAspect {

    @Before("my.project.msa.order_service.aop.PointCuts.allOrderController()")
    public void beforeAdvice(JoinPoint point){
        log.info("Call : {} , params : {} ", point.getSignature(), point.getArgs());
    }
}
