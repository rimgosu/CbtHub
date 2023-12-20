package rimgosu.cbthub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rimgosu.cbthub.domain.question.Question;
import rimgosu.cbthub.domain.round.Round;
import rimgosu.cbthub.repository.QuestionRepository;
import rimgosu.cbthub.repository.RoundRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    /**
     * 카테고리 등록
     */
    @Transactional
    public Long register(Question question) {
        validataDuplicateRound(question); // 중복회원 검증
        questionRepository.save(question);
        return question.getId();
    }

    private void validataDuplicateRound(Question question) {
        // EXCEPTION
        List<Question> findMembers = questionRepository.findByNumber(question.getNumber());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 문항입니다.");
        }
    }

    public List<Question> findMembers() {
        return questionRepository.findAll();
    }

    public Question findOne(Long questionId) {
        return questionRepository.findOne(questionId);
    }

    public List<Question> findByNumber(int number) {
        return questionRepository.findByNumber(number);
    }

    public List<Question> findByRoundId(Long roundId) {
        return questionRepository.findByRoundId(roundId);
    }
}
