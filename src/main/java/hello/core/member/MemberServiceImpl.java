package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 구현 객체 선택 (그렇지 않으면, nullPointException 발생)
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 생성자를 통해서 어떤 구현체를 사용할지를 AppConfig에서 설정해 줌 (생성자 주입)
    // 이렇게 수정함으로써, MemberServiceImpl에서 MemoryMemberRepository에 대한 코드가 없어지기 때문에, 추상화에만 의존하게 됨 (DIP 만족)
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        // 다형성에 의해서 인터페이스인 MemberRepository를 호출하는 것이 아닌, 오버라이딩된 MemoryMemberRepository를 호출
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        // 다형성에 의해서 인터페이스인 MemberRepository를 호출하는 것이 아닌, 오버라이딩된 MemoryMemberRepository를 호출
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
