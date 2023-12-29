package rimgosu.cbthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rimgosu.cbthub.domain.logs.QuestionLog;

import java.util.List;

@Repository
public interface QuestionLogRepository extends JpaRepository<QuestionLog, Long> {
    // question_id를 이용하여 QuestionLog를 찾는 메서드
    List<QuestionLog> findByQuestion_Id(Long questionId);
}
