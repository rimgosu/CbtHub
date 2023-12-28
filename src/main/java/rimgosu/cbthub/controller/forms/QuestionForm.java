package rimgosu.cbthub.controller.forms;

import lombok.*;
import rimgosu.cbthub.domain.question.OX;
import rimgosu.cbthub.domain.question.QuestionType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class QuestionForm {

    private int number;

    private QuestionType questionType;
    private String whatQuestion;
    private String photo;
    private OX oxChoiceAnswer;
    private String subjectiveAnswer;
    private String commentary;
    private String gptCommentary;
    private Long roundId;

    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;

    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String choice5;

    private OX multipleChoiceAnswer1;
    private OX multipleChoiceAnswer2;
    private OX multipleChoiceAnswer3;
    private OX multipleChoiceAnswer4;
    private OX multipleChoiceAnswer5;

}
