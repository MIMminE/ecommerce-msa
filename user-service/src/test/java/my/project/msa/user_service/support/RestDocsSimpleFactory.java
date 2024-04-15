package my.project.msa.user_service.support;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestDocsSimpleFactory implements RestDocsFactory {

    Set<Class<?>> requestSet = new HashSet<>();
    Set<Class<?>> responseSet = new HashSet<>();

    @Override
    public RestDocumentationResultHandler document(String identifier, Class<?> requestClass, Class<?> responseClass) {

        RestDocumentationResultHandler document = MockMvcRestDocumentation.document(identifier,
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                this.requestFields(requestClass),
                this.responseFields(responseClass));
        return document;

    }

    public RestDocsSimpleFactory(Class<?>... clazz) {
        for (Class<?> aClass : clazz) {
            RestDocsHolder annotation = aClass.getAnnotation(RestDocsHolder.class);
            try {
                switch (annotation.value()) {
                    case request -> requestSet.add(aClass);
                    case response -> responseSet.add(aClass);
                    default -> log.info("Use Annotation 'RestDocsHolder' :: {}", aClass);
                }
            } catch (NullPointerException e) {
                log.debug("Use Annotation 'RestDocsHolder' :: {}", aClass);
                throw e;
            }
        }
    }

    @Override
    public RequestFieldsSnippet requestFields(Class<?> requestClass) {

        for (Class<?> aClass : requestSet) {
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.getType().isAssignableFrom(requestClass)) {
                    RequestFieldsSnippet result = PayloadDocumentation.requestFields();
                    Function<RequestFieldsSnippet, RequestFieldsSnippet> snippetFunction = null;

                    if (declaredField.isAnnotationPresent(RestDocsSnippet.class)) {
                        RestDocsSnippet requestSnippet = declaredField.getAnnotation(RestDocsSnippet.class);

                        snippetFunction = snippetFunction(requestSnippet);
                    }
                    return Objects.requireNonNull(snippetFunction).apply(result);
                }
            }
        }

        return null;
    }

    @Override
    public ResponseFieldsSnippet responseFields(Class<?> responesClass) {
        System.out.println("call");
        for (Class<?> aClass : responseSet) {
            System.out.println("call2");
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.getType().isAssignableFrom(responesClass)) {
                    System.out.println("call3");
                    ResponseFieldsSnippet result = PayloadDocumentation.responseFields();
                    Function<ResponseFieldsSnippet, ResponseFieldsSnippet> snippetFunction = null;

                    if (declaredField.isAnnotationPresent(RestDocsSnippet.class)) {
                        RestDocsSnippet responseSnippet = declaredField.getAnnotation(RestDocsSnippet.class);

                        snippetFunction = responseSnippetFunction(responseSnippet);
                    }
                    return Objects.requireNonNull(snippetFunction).apply(result);
                }
            }
        }

        return null;
    }

    private Function<ResponseFieldsSnippet, ResponseFieldsSnippet> responseSnippetFunction(RestDocsSnippet responseSnippet) {
        Function<ResponseFieldsSnippet, ResponseFieldsSnippet> snippetFunction;
        snippetFunction = snippet -> {
            for (RestDocsField v : responseSnippet.value()) {
                snippet = snippet.and(getDescription(v));
            }
            return snippet;
        };
        return snippetFunction;
    }


    private Function<RequestFieldsSnippet, RequestFieldsSnippet> snippetFunction(RestDocsSnippet requestSnippet) {
        Function<RequestFieldsSnippet, RequestFieldsSnippet> snippetFunction;
        snippetFunction = snippet -> {
            for (RestDocsField v : requestSnippet.value()) {
                snippet = snippet.and(getDescription(v));
            }
            return snippet;
        };
        return snippetFunction;
    }

    private FieldDescriptor getDescription(RestDocsField v) {

        FieldDescriptor description = PayloadDocumentation
                .fieldWithPath(v.name())
                .type(v.type())
                .description(v.description());

        if (v.optional())
            description.optional();

        return description;
    }
}
