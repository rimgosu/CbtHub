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

    @ElementCollection
    private List<String> options;

    @ElementCollection
    private List<String> choices;

    Boolean isO;
    int multipleChoiceAnswer;
    String subjectiveAnswer;
    String commentary;
    String gptCommentary;

    Date registerDate;

    @OneToMany(mappedBy = "member")
    private List<QuestionComment> questionComments;

    @OneToOne(mappedBy = "question", fetch = LAZY)
    private QuestionLog questionLog;


}
