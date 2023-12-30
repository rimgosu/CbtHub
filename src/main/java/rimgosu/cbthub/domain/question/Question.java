package rimgosu.cbthub.domain.question;

import lombok.*;
import rimgosu.cbthub.domain.board.QuestionComment;
import rimgosu.cbthub.domain.logs.QuestionLog;
import rimgosu.cbthub.domain.round.Round;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "round_id")
    private Round round;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    private int number;
    private String whatQuestion;
    private String photo;

    @Embedded
    private Options options;

    @Embedded
    private Choices choices;

    @Enumerated(EnumType.STRING)
    private OX oxChoiceAnswer;

    @Embedded
    private MultipleChoiceAnswers multipleChoiceAnswers;
    private String subjectiveAnswer;
    private String commentary;
    private String gptCommentary;

    private Date registerDate;

    @OneToMany(mappedBy = "member")
    private List<QuestionComment> questionComments;

    @OneToMany(mappedBy = "question", fetch = LAZY)
    private List<QuestionLog> questionLogs;



    //==문제 등록==//
    public Question(int number,
            QuestionType type,
            String whatQuestion,
            String photo,
            Options options,
            Choices choices,
            OX oxChoiceAnswer,
            MultipleChoiceAnswers multipleChoiceAnswers,
            String subjectiveAnswer,
            String commentary,
            String gptCommentary,
            Round round) {
        this.number = number;
        this.type = type;
        this.whatQuestion = whatQuestion;
        this.photo = photo;
        this.options = options;
        this.choices = choices;
        this.oxChoiceAnswer = oxChoiceAnswer;
        this.multipleChoiceAnswers = multipleChoiceAnswers;
        this.subjectiveAnswer = subjectiveAnswer;
        this.commentary = commentary;
        this.gptCommentary = gptCommentary;
        this.round = round;
    }

}
