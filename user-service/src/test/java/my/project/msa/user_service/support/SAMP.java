package my.project.msa.user_service.support;

import lombok.Builder;
import lombok.ToString;
import my.project.msa.user_service.domain_model.Group;
import my.project.msa.user_service.domain_model.User;
import nutslib.fixture.DefaultFixtureFactoryBuilder;
import nutslib.fixture.FixtureFactory;
import nutslib.fixture.FixtureFactoryBuilder;
import nutslib.fixture.fieldconfig.StringConfigurer;
import nutslib.fixture.inspector.*;
import org.h2.engine.UserBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    static class lClass {
        Long s;
        String n;
    }

    @ToString
    @Builder
    static class lowClass {
        int number;
        Long size;
        String name;
        lClass lClass;
    }

    @ToString
    @Builder
    static class testClass {
        String userId;
        int number;
        List<lowClass> lowClass;
        List<String> strs;
        lowClass aClass;
    }

    @Test
    void test() throws ClassNotFoundException {

        ClassInspector classInspector = new ClassInspector(new ExpressionClassHolder(Group.class, "testClass"));

        List<FieldInfo> filter = classInspector.filter(new DefaultFilter());

        for (FieldInfo fieldInfo : filter) {
            System.out.println(fieldInfo.getExpression());

        }


        // given
        FixtureFactoryBuilder<testClass> builder = new DefaultFixtureFactoryBuilder<>(testClass.class);
        builder.set("userId", strings().alpha().ofMinLength(10).ofMaxLength(20));
//        builder.set("lowClass[*].name", strings().alpha().ofMinLength(10).ofMaxLength(20));
//        builder.set("strs[*]", strings().alpha().ofMinLength(10).ofMaxLength(20));

        FixtureFactory<testClass> factory = builder.build();
        Iterable<testClass> fixtures = factory.getFixtures(10);
        for (testClass fixture : fixtures) {
            System.out.println(fixture);
        }
//
//
//        ClassInspector classInspector = new ClassInspector(testClass.class);
//
//
//
//        classInspector.inspectField().howType(Primitive).inspect();
//
//        ClassInsepctor.inspector(testClass).allField().userClass().inspect();
//        ClassInsepctor.inspector(testClass).allField().collection().inspect();
//        ClassInsepctor.inspector(testClass).allField().collection().inspect();
//


//        FixtureFactory<User> factory2 = new FixtureFactoryBuilder(User.class, config).build();


//        factory.getFixture().set("userId", 10L).set("groupName", "test");
//        factory.getFixtures(100).set("userId", 20L).set("items[*].itemName", "product");


        // when

        // then
    }




    @Test
    void test3() {
        ClassInspector classInspector = new ClassInspector(new ExpressionClassHolder(Group.class, "group3"));

        List<FieldInfo> filter = classInspector.filter(new DefaultFilter());

        for (FieldInfo fieldInfo : filter) {
            System.out.println(fieldInfo);
        }




    }

}

