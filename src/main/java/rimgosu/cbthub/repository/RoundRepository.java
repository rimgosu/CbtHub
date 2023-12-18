package rimgosu.cbthub.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import rimgosu.cbthub.domain.round.Round;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoundRepository {

    private final EntityManager em;

    public void save(Round round) {
        em.persist(round);
    }

    public Round findOne(Long id) {
        return em.find(Round.class, id);
    }

    public List<Round> findAll() {
        return em.createQuery("select r from Round r", Round.class)
                .getResultList();
    }

    public List<Round> findByName(String roundName) {
        return em.createQuery("select r from Round r where r.roundName = :roundName", Round.class)
                .setParameter("roundName", roundName)
                .getResultList();
    }

}
