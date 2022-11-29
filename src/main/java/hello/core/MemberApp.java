package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    // psvm 치고 enter하면 바로 생성됨... wow..
    public static void main(String[] args) {
        // MemberService memberService = new MemberServiceImpl();
        // 직접 생성하지 않고, AppConfig를 통해 객체 생성
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();

        // Spring 전환 (AppConfig에 있는 Class들을 스프링 컨테이너에 등록, 관리)
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 스프링 컨테이너에 등록된 MemberService Class
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // 변수 자동 생성
        // windows => ctrl + alt + V
        // mac => command + option + V
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("newMember => " + member.getName());
        System.out.println("findMember => " + findMember.getName());
    }
}
