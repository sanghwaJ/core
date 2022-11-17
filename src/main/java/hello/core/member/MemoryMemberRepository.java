package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    // 실무에선 동시성 이슈 때문에 ConcurrentHashMap을 사용함 (예제이기 때문에 HashMap 사용...)
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }
    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
