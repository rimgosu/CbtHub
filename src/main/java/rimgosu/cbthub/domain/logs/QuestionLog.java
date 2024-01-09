package rimgosu.cbthub.domain.logs;

import lombok.*;
import rimgosu.cbthub.domain.Member.Member;
import rimgosu.cbthub.domain.question.MultipleChoiceAnswers;
import rimgosu.cbthub.domain.question.OX;
import rimgosu.cbthub.domain.question.Question;
import rimgosu.cbthub.domain.round.Round;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
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

    @ManyToOne(fetch = LAZY)
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


    //==연관관계 매서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getQuestionLogs().add(this);
    }

    public void setQuestion(Question question) {
        this.question = question;
        question.getQuestionLogs().add(this);
    }

    public void setRoundLog(RoundLog roundLog) {
        this.roundLog = roundLog;
        roundLog.getQuestionLogs().add(this);
    }




    //== 등록 ==//

    public QuestionLog(Member member, Question question, RoundLog roundLog, QuestionLogType questionLogType,
                       MultipleChoiceAnswers choseMultipleChoiceAnswers, OX choseOxAnswer, String choseSubjectiveAnswer) {
        QuestionLog questionLog = new QuestionLog();
        questionLog.setMember(member);
        questionLog.setQuestion(question);
        questionLog.setRoundLog(roundLog);
        questionLog.setQuestionLogType(questionLogType);
        questionLog.setChoseMultipleChoiceAnswers(choseMultipleChoiceAnswers);
        questionLog.setChoseOxAnswer(choseOxAnswer);
        questionLog.setChoseSubjectiveAnswer(choseSubjectiveAnswer);
    }

    //== 수정 ==//
    public void updateQuestionLog(QuestionLogType questionLogType, MultipleChoiceAnswers choseMultipleChoiceAnswers,
                       OX choseOxAnswer, String choseSubjectiveAnswer){
        this.questionLogType = questionLogType;
        this.choseMultipleChoiceAnswers = choseMultipleChoiceAnswers;
        this.choseOxAnswer = choseOxAnswer;
        this.choseSubjectiveAnswer = choseSubjectiveAnswer;
        this.solveDate = new Date();
    }

}
