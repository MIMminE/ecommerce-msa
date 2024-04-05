package my.project.msa.user_service.aop;

import org.aspectj.lang.annotation.Pointcut;

public abstract class PointCuts {
    @Pointcut("execution(* my.project..UserController.*(..))")
    static void allUserController(){}

    @Pointcut("execution(* my.project..UserService.*(..))")
    static void allUserService(){}
}
