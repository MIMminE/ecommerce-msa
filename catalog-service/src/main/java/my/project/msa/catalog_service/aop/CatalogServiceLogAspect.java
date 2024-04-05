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
public class CatalogServiceLogAspect {

    @Before("my.project.msa.catalog_service.aop.PointCuts.allService()")
    public void beforeAdvice(JoinPoint point){
        log.info("Service call {} , param {}", point.getSignature().toShortString(), point.getArgs());
    }

    @AfterReturning(value = "my.project.msa.catalog_service.aop.PointCuts.allService()", returning = "result")
    public void afterReturningAdvice(JoinPoint point, Object result){
        log.info("Service {} return {}", point.getSignature().toShortString(), result.toString());
    }
}
