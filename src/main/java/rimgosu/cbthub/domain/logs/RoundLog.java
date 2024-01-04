package rimgosu.cbthub.domain.logs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rimgosu.cbthub.domain.Member.Member;
import rimgosu.cbthub.domain.round.Round;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoundLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_log_id")
    private Long id;

    // 회원별 회차 진척도
    private int wrongNumber;
    private int correctNumber;
    private int wrongCorrectNumber;
    private float roundProgressPercent;
    
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    private Round round;

    @OneToMany(mappedBy = "roundLog")
    private List<QuestionLog> questionLogs;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_log_id")
    private CategoryLog categoryLog;

    //== 등록 ==//
    public RoundLog(int wrongNumber, int correctNumber, int wrongCorrectNumber, float roundProgressPercent,
                    Member member, Round round, ) {

    }

}
