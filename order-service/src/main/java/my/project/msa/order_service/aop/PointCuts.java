package my.project.msa.order_service.aop;

import org.aspectj.lang.annotation.Pointcut;

public abstract class PointCuts {
    @Pointcut("execution(* my.project..OrderController.*(..))")
    static void allOrderController(){}

    @Pointcut("execution(* my..OrderServiceExceptionHandler.*(..))")
    static void allExceptionHandler(){}
}
