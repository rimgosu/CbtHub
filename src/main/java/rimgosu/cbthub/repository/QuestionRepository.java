package rimgosu.cbthub.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import rimgosu.cbthub.domain.question.Question;
import rimgosu.cbthub.domain.round.Round;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepository {

    private final EntityManager em;

    public void save(Question question) {
        em.persist(question);
    }

    public Question findOne(Long id) {
        return em.find(Question.class, id);
    }

    public List<Question> findAll() {
        return em.createQuery("select q from Question q", Question.class)
                .getResultList();
    }

    public List<Question> findByNumber(int number) {
        return em.createQuery("select q from Question q where q.number = :number", Question.class)
                .setParameter("number", number)
                .getResultList();
    }

    public List<Question> findByRoundId(Long roundId) {
        return em.createQuery("select q from Question q join q.round c where c.id = :roundId", Question.class)
                .setParameter("roundId", roundId)
                .getResultList();
    }

}
