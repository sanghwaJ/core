package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
        // MemberService memberService = new MemberServiceImpl();
        // OrderService orderService = new OrderServiceImpl();

        // 직접 생성하지 않고, AppConfig를 통해 객체 생성
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();
        // OrderService orderService = appConfig.orderService();

        // Spring 전환 (AppConfig에 있는 Class들을 스프링 컨테이너에 등록, 관리)
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 스프링 컨테이너에 등록된 MemberService, OrderService Class
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order => " + order);
        System.out.println("order.calculatePrice => " + order.calculatePrice());
    }
}
