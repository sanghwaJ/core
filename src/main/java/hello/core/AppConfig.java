package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Spring 컨테이너 등록
public class AppConfig {

    // 생성자를 통해서 어떤 구현체를 사용할지를 AppConfig에서 설정해 줌 (생성자 주입)
    // MemberServiceImpl => MemoryMemberRepository
    // @Bean memberService => new MemoryMemberRepository 호출
    @Bean // Spring 컨테이너 등록
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    // 리팩토링 => MemberRepository 중복을 제거하고, 각 메소드가 어떤 역할을 하는지 알기 쉬워짐
    @Bean // Spring 컨테이너 등록
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    // OrderServiceImpl => MemoryMemberRepository, FixDiscountPolicy
    // @Bean orderService => new MemoryMemberRepository 호출
    @Bean // Spring 컨테이너 등록
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 리팩토링 => 각 메소드가 어떤 역할을 하는지 알기 쉬워짐
    @Bean // Spring 컨테이너 등록
    public DiscountPolicy discountPolicy() {
        // 할인 정책이 변경되면, AppConfig의 이 부분만 고치면 됨 
        // 사용 영역 코드는 변경할 필요가 없고, 구성 영역 코드만 고치면 된다!
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
