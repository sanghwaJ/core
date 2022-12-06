package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // Spring Bean 등록
public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 1) DIP 위반 => 추상(인터페이스) 뿐만 아니라 구체(구현) 클래스에도 의존하고 있음
    // 추상(인터페이스) => DiscountPolicy / 구체(구현) 클래스 => FixDiscountPolicy, RateDiscountPolicy
    // OrderServiceImpl은 인터페이스인 DiscountPolicy와 FixDiscountPolicy, RateDiscountPolicy와 같은 구체 클래스도 의존하고 있음
    // 2) OCP 위반 => 클라이언트 코드를 변경하지 않고 확장할 수 없음 (지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 줌)
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 해결법 => DIP를 위반하지 않게 인터페이스에만 의존하도록 의존관계를 변경한다!
    // AppConfig에서 OrderServiceImpl에 DiscountPolicy 구현 객체를 대신 생성하고 주입해주어야 함
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 생성자가 딱 1개만 있으면 @Autowired를 생략해도 자동 주입 된다. 물론 스프링 빈에만 해당한다.
    // @Autowired // 자동으로 의존관계 주입 (= ac.getBean(MemberRepository.class) && ac.getBean(DiscountPolicy.class))
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // 단일 책임 원칙을 잘 지킨 CASE => 할인과 관련된 것은 discountPolicy에서 담당!
        // 만일 단일 책임 원칙을 잘 지키지 못한 경우, 할인 관련된 수정이 필요할 때도 OrderService를 고쳐야 하는 경우가 생김
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
