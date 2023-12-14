package rimgosu.cbthub.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import rimgosu.cbthub.domain.Member.Member;
import rimgosu.cbthub.domain.Member.MemberGrade;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Test
    public void 회원가입() throws Exception{
        // given
        Member member = new Member();
        member.setUsername("test@gmail.com");
        member.setGrade(MemberGrade.PREMIUM);
        member.setNickname("임꺽정");

        // when
        memberRepository.save(member);

        // then
        List<Member> members = memberRepository.findAll();
        System.out.println("members : " + members.toString());
    }
}