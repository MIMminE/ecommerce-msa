package my.project.msa.user_service.support;

import org.springframework.restdocs.payload.JsonFieldType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface RestDocsField {
    String name();
    JsonFieldType type();
    String description();
    boolean optional() default false;
}
