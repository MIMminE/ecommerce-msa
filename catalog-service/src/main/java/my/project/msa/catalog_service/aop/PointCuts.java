package my.project.msa.catalog_service.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {

    @Pointcut("execution(* my..CatalogController.*(..))")
    static public void allController(){}

    @Pointcut("execution(* my..CatalogService.*(..))")
    static public void allService(){}
}
