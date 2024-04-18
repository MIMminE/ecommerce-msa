package my.project.msa.user_service.support;

import lombok.Builder;
import lombok.ToString;
import my.project.msa.user_service.domain_model.User;
import nutslib.fixture.DefaultFixtureFactoryBuilder;
import nutslib.fixture.FixtureFactory;
import nutslib.fixture.FixtureFactoryBuilder;
import nutslib.fixture.fieldconfig.StringConfigurer;
import org.h2.engine.UserBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static net.jqwik.api.Arbitraries.integers;
import static net.jqwik.api.Arbitraries.strings;

public class SAMP {

    static class FixtureProxy {

        public FixtureProxy() {
        }

        public FixtureProxyBuilder<?> getBuilder(Class<?> clazz) {
            return this.createBuilder(new FixtureProxyBuilderConfigurer(clazz));
        }

        public FixtureProxyBuilder<?> getBuilder(Class<?> clazz, FixtureFieldOption option) {
            return this.createBuilder(new FixtureProxyBuilderConfigurer(clazz, option));
        }

        private FixtureProxyBuilder<?> createBuilder(FixtureProxyBuilderConfigurer configurer) {
            return new FixtureProxyBuilder<>(configurer);
        }
    }

    static class FixtureProxyBuilder<T> {
        FixtureProxyBuilderConfigurer configurer;

        public FixtureProxyBuilder(FixtureProxyBuilderConfigurer configurer) {
            this.configurer = configurer;
        }

    }

    @ToString
    @Builder
    static class lClass{
        Long s;
        String n;
    }

    @ToString
    @Builder
    static class lowClass{
        int number;
        Long size;
        String name;
        lClass lClass;
    }

    @ToString
    @Builder
    static class testClass{
        String userId;
        int number;
        List<lowClass> lowClass;
        List<String> strs;
        lowClass aClass;
    }

    @Test
    void test() throws ClassNotFoundException {
        // given
        FixtureFactoryBuilder<testClass> builder = new DefaultFixtureFactoryBuilder<>(testClass.class);
//        builder.set("userId", strings().alpha().ofMinLength(10).ofMaxLength(20));
//        builder.set("lowClass[*].name", strings().alpha().ofMinLength(10).ofMaxLength(20));
//        builder.set("strs[*]", strings().alpha().ofMinLength(10).ofMaxLength(20));

        FixtureFactory<testClass> factory = builder.build();
        Iterable<testClass> fixtures = factory.getFixtures(10);
        for (testClass fixture : fixtures) {
            System.out.println(fixture);
        }


//        FixtureFactory<User> factory2 = new FixtureFactoryBuilder(User.class, config).build();


//        factory.getFixture().set("userId", 10L).set("groupName", "test");
//        factory.getFixtures(100).set("userId", 20L).set("items[*].itemName", "product");


        // when

        // then
    }
}

