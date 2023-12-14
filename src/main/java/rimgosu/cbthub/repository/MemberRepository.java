package rimgosu.cbthub.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import rimgosu.cbthub.domain.Member.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String username) {
        return em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", username)
                .getResultList();
    }

    public List<Member> findByNamePassword(String username, String password) {
        return em.createQuery("select m from Member m where m.username = :username and m.password = :password", Member.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();
    }


}
