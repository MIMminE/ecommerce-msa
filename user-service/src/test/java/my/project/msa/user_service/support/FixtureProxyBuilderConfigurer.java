package my.project.msa.user_service.support;

import java.util.List;
import java.util.Map;

public class FixtureProxyBuilderConfigurer {
    List<String> excludeFields;
    Class<?> aClass;

    public <T> FixtureProxyBuilderConfigurer(Class<T> clazz) {
        this.aClass = clazz;
    }

    public <T> FixtureProxyBuilderConfigurer(Class<T> clazz, FixtureFieldOption option) {
        this.aClass = clazz;
    }

//    Map<String, List<FixtureOption>> fieldsConfiguration;
}
