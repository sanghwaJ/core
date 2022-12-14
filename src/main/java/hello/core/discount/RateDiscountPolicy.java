package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component // Spring Bean 등록
// @Primary // 우선권을 가짐
// annotation을 만들어 적용하여 컴파일 시 타입 체크가 안되는 @Qualifier("mainDiscountPolicy") 문제 해결
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }

    // 테스트 코드 쉽게 만들기 => windows : ctrl + shift + T / mac : command + shift + T
}
