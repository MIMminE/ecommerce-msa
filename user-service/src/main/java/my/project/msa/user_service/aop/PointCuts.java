package my.project.msa.user_service.aop;

import org.aspectj.lang.annotation.Pointcut;

public abstract class PointCuts {
    @Pointcut("execution(* my.project..*Controller.*(..))")
    static void allUserController(){}

    @Pointcut("execution(* my.project..*Service.*(..))")
    static void allUserService(){}
}
