package my.project.msa.user_service.support;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(RestDocumentationExtension.class)
public abstract class RestDocsSupport {

    protected MockMvc mockMvc;
    protected RestDocsFactory restDocsFactory = new RestDocsSimpleFactory(RequestHolder.class, ResponseHolder.class);

    @BeforeEach
    void setUp(RestDocumentationContextProvider provider){
        this.mockMvc = MockMvcBuilders.standaloneSetup(initController())
                .apply(MockMvcRestDocumentation.documentationConfiguration(provider)).build();
    }

    protected abstract Object initController();
}

