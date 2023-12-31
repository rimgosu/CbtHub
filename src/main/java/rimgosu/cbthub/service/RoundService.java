package rimgosu.cbthub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rimgosu.cbthub.domain.round.Round;
import rimgosu.cbthub.repository.RoundRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoundService {

    private final RoundRepository roundRepository;

    /**
     * 카테고리 등록
     */
    @Transactional
    public Long register(Round round) {
        validataDuplicateRound(round); // 중복회원 검증
        roundRepository.save(round);
        return round.getId();
    }

    private void validataDuplicateRound(Round round) {
        // EXCEPTION
        List<Round> findMembers = roundRepository.findByName(round.getRoundName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회차입니다.");
        }
    }

    public List<Round> findMembers() {
        return roundRepository.findAll();
    }

    public Round findOne(Long id) {
        return roundRepository.findOne(id);
    }

    public List<Round> findByName(String RoundName) {
        return roundRepository.findByName(RoundName);
    }

    public List<Round> findByCategoryId(Long categoryId) {
        return roundRepository.findByCategoryId(categoryId);
    }

    @Transactional
    public void plusOneLastNumber(Long roundId, int number) {
        Round findRound = roundRepository.findOne(roundId);
        findRound.setLastQuestionNumber(number);
    }

}
