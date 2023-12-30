package rimgosu.cbthub.domain.logs;

import lombok.*;
import rimgosu.cbthub.domain.Member.Member;
import rimgosu.cbthub.domain.question.MultipleChoiceAnswers;
import rimgosu.cbthub.domain.question.OX;
import rimgosu.cbthub.domain.question.Question;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_log_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "question_id")
    private Question question;

    @Enumerated(EnumType.STRING)
    private QuestionLogType questionLogType;

    @Embedded
    private MultipleChoiceAnswers choseMultipleChoiceAnswers;
    private OX choseOxAnswer;
    private String choseSubjectiveAnswer;

    private Date solveDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "round_log_id")
    private RoundLog roundLog;

    public QuestionLog(Member member, Question question, QuestionLogType questionLogType, MultipleChoiceAnswers choseMultipleChoiceAnswers,
                                    OX choseOxAnswer, String choseSubjectiveAnswer){
        this.member = member;
        this.question = question;
        this.questionLogType = questionLogType;
        this.choseMultipleChoiceAnswers = choseMultipleChoiceAnswers;
        this.choseOxAnswer = choseOxAnswer;
        this.choseSubjectiveAnswer = choseSubjectiveAnswer;
        this.solveDate = new Date();
    }

}
