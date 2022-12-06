package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 컴포넌트 스캔 시작 패키지 지정
        // basePackages = "hello.core.member",
        // 컴포넌트 스캔 시작 클래스 지정
        // basePackageClasses = AutoAppConfig.class,
        // 컴포넌트 스캔 default => AutoAppConfig가 속한 패키지인 hello.core부터 컴포넌트 스캔 시작

        // AppConfig.java 파일을 제외시킴 (Configuration 어노테이션이 붙은 것을 제외)
        excludeFilters= @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
