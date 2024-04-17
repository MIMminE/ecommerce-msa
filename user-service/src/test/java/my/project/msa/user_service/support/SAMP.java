package my.project.msa.user_service.support;

import my.project.msa.user_service.domain_model.User;
import nutslib.fixture.FixtureFactoryBuilder;
import org.h2.engine.UserBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

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


    @Test
    void test() {
        // given

        FixtureFactory<User> factory = new FixtureFactoryBuilder(User.class)
                .set("userId").strings().withCharRange('a', 'z').ofMinLength(5).ofMaxLength(10)
                .set("userId").strings().withCharRange('a', 'z').ofMinLength(5).ofMaxLength(10)
                .build();

        FixtureFactory<User> factory2 = new FixtureFactoryBuilder(User.class, config).build();

        FixtureFactory<User> factory3 = new FixtureFactoryBuilder(User.class, config)
                .setFixture(User.class.build("..."), 10)

                .build();

        factory.getFixture().set("userId", 10L).set("groupName", "test");
        factory.getFixtures(100).set("userId",20L).set("items[*].itemName","product");



        // when

        // then
    }
}

