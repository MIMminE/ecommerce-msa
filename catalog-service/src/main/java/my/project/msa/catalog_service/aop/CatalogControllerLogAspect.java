package my.project.msa.catalog_service.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CatalogControllerLogAspect {

    @Before("my.project.msa.catalog_service.aop.PointCuts.allController()")
    public void beforeAspect(JoinPoint point){
        log.info("Controller call {} , param {}", point.getSignature().toShortString(), point.getArgs());
    }

    @AfterReturning(value = "my.project.msa.catalog_service.aop.PointCuts.allController()", returning = "result")
    public void afterAspect(JoinPoint point, Object result){
        log.info("Controller {} return {}", point.getSignature().toShortString(), result.toString());
    }
}
