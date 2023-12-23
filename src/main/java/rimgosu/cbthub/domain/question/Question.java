package rimgosu.cbthub.domain.question;

import lombok.*;
import rimgosu.cbthub.domain.board.QuestionComment;
import rimgosu.cbthub.domain.logs.QuestionLog;
import rimgosu.cbthub.domain.round.Round;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ElementCollection
    private List<String> options = new ArrayList<>();

    @ElementCollection
    private List<String> choices = new ArrayList<>();

    private CorrectWrong oxChoiceAnswer;

    @ElementCollection
    private List<CorrectWrong> multipleChoiceAnswers = new ArrayList<>();
    private String subjectiveAnswer;
    private String commentary;
    private String gptCommentary;

    private Date registerDate;

    @OneToMany(mappedBy = "member")
    private List<QuestionComment> questionComments;

    @OneToOne(mappedBy = "question", fetch = LAZY)
    private QuestionLog questionLog;



    //==문제 등록==//
    public Question(int number,
            QuestionType type,
            String whatQuestion,
            String photo,
            List<String> options,
            List<String> choices,
            CorrectWrong oxChoiceAnswer,
            List<CorrectWrong> multipleChoiceAnswers,
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
