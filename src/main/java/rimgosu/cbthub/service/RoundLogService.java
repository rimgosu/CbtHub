package rimgosu.cbthub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rimgosu.cbthub.domain.logs.QuestionLog;
import rimgosu.cbthub.domain.logs.QuestionLogType;
import rimgosu.cbthub.domain.logs.RoundLog;
import rimgosu.cbthub.domain.question.MultipleChoiceAnswers;
import rimgosu.cbthub.repository.QuestionLogRepository;
import rimgosu.cbthub.repository.RoundLogRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoundLogService {

    private final RoundLogRepository roundLogRepository;
    private final QuestionLogService questionLogService;
    /**
     * RoundLog 등록
     */
    @Transactional
    public Long register(RoundLog roundLog, QuestionLog questionLog) {



        roundLogRepository.save(roundLog);
        return roundLog.getId();
    }


    public List<RoundLog> findAll() {
        return roundLogRepository.findAll();
    }

    public Optional<RoundLog> findById(Long roundLogId) {
        return roundLogRepository.findById(roundLogId);
    }

    public List<RoundLog> findByQuestion_Id(Long questionId) {
        return roundLogRepository.findByRound_Id(questionId);
    }

    public RoundLog findByMemberUsername(String username) {
        return roundLogRepository.findByMemberUsername(username);
    }


}
