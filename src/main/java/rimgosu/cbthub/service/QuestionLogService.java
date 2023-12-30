package rimgosu.cbthub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rimgosu.cbthub.domain.logs.QuestionLog;
import rimgosu.cbthub.domain.logs.QuestionLogType;
import rimgosu.cbthub.domain.question.MultipleChoiceAnswers;
import rimgosu.cbthub.repository.QuestionLogRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionLogService {

    private final QuestionLogRepository questionLogRepository;

    /**
     * 카테고리 등록
     */
    @Transactional
    public Long register(QuestionLog questionLog) {
        questionLogRepository.save(questionLog);
        return questionLog.getId();
    }


    public List<QuestionLog> findAll() {
        return questionLogRepository.findAll();
    }

    public Optional<QuestionLog> findById(Long questionLogId) {
        return questionLogRepository.findById(questionLogId);
    }

    public List<QuestionLog> findByQuestionId(Long questionId) {
        return questionLogRepository.findByQuestion_Id(questionId);
    }

    public QuestionLogType checkCorrect(MultipleChoiceAnswers multipleChoiceAnswers, MultipleChoiceAnswers choseMultipleChoiceAnswers) {
        if (multipleChoiceAnswers.equals(choseMultipleChoiceAnswers)) {
            return QuestionLogType.ANSWER_CORRECT;
        } else {
            return QuestionLogType.ANSWER_WRONG;
        }
    }
}
