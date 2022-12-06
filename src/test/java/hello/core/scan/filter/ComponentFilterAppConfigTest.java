package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        /* BeanB는 컴포넌트 스캔 대상에서 제외되었기 때문에 아래의 코드는 에러가 발생함
        BeanB beanB = ac.getBean("beanB", BeanB.class);
        assertThat(beanB).isNotNull();
        */

        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );
    }


    @Configuration
    @ComponentScan(
            // includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            // excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)

            // Filter Type은 default가 ANNOTATION이기 때문에 아래와 같이 사용해도 위와 같음
            includeFilters = @Filter(classes = MyIncludeComponent.class), // import static으로 뺄 수 있음
            excludeFilters = @Filter(classes = MyExcludeComponent.class) // import static으로 뺄 수 있음
    )
    static class ComponentFilterAppConfig {

    }
}
