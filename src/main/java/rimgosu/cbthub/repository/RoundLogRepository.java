package rimgosu.cbthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rimgosu.cbthub.domain.logs.QuestionLog;
import rimgosu.cbthub.domain.logs.RoundLog;
import rimgosu.cbthub.domain.round.Round;

import java.util.List;

@Repository
public interface RoundLogRepository extends JpaRepository<RoundLog, Long> {
    // question_id를 이용하여 QuestionLog를 찾는 메서드
    List<RoundLog> findByRound_Id(Long roundId);
    RoundLog findTopByMemberIdOrderByIdDesc(Long memberId);

    RoundLog findByMemberUsername(String memberId);



}
