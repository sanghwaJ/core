package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 구현 객체 선택 (그렇지 않으면, nullPointException 발생)
    private final MemberRepository memberRepository = new MemoryMemberRepository();

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
}
