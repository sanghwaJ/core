package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        // 자동 주입 대상을 옵션으로 처리하는 방법
        
        // 방법 1) @Autowired(required = false) => 메소드 자체가 호출이 안됨
        @Autowired(required = false) // default는 required = true
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }
        
        // 방법 2) org.springframework.lang.@Nullable => 자동 주입할 대상이 없으면 null 입력
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }
        
        // 방법 3) Optional<> => 자동 주입할 대상이 없으면 Optional.empty 입력
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
