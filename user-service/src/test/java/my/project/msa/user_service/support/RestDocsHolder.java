package my.project.msa.user_service.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static my.project.msa.user_service.support.RestDocsHolder.RestDocsHolderType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RestDocsHolder {
    RestDocsHolderType value() default request;

    enum RestDocsHolderType {
        request,
        response
    }
}
