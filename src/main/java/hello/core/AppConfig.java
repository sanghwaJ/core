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

public class AppConfig {

    // 생성자를 통해서 어떤 구현체를 사용할지를 AppConfig에서 설정해 줌 (생성자 주입)
    // MemberServiceImpl => MemoryMemberRepository
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    // 리팩토링 => MemberRepository 중복을 제거하고, 각 메소드가 어떤 역할을 하는지 알기 쉬워짐
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // OrderServiceImpl => MemoryMemberRepository, FixDiscountPolicy
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 리팩토링 => 각 메소드가 어떤 역할을 하는지 알기 쉬워짐
    private DiscountPolicy discountPolicy() {
        // 할인 정책이 변경되면, AppConfig의 이 부분만 고치면 됨 
        // 사용 영역 코드는 변경할 필요가 없고, 구성 영역 코드만 고치면 된다!
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
