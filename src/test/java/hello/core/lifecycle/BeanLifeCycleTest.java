package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void LifeCycleTest() {
        // ConfigurableApplicationContext가 AnnotationConfigApplicationContext보다 상위 인터페이스이기 때문에 가능
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);

        // 원래는 이렇게 close 해주어야 함
        // 기존 사용하던 ApplicationContext에선 close 사용이 불가하니, ConfigurableApplicationContext나 AnnotationConfigApplicationContext 사용
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        /* 방법 1 : 인터페이스 (InitializingBean, DisposableBean) */
        // @Bean

        /* 방법 2 : 빈 등록 초기화, 소멸 메서드 지정 */
        // @Bean(initMethod = "init", destroyMethod = "close")

        /* 방법 3 : @PostConstruct, @PreDestroy */
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

}
