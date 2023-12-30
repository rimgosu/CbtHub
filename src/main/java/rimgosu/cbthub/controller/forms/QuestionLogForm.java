package rimgosu.cbthub.controller.forms;

import lombok.*;
import rimgosu.cbthub.domain.logs.QuestionLogType;
import rimgosu.cbthub.domain.question.OX;
import rimgosu.cbthub.domain.question.QuestionType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class QuestionLogForm {

    private Long questionId;

    private QuestionLogType questionLogType;
    private QuestionType questionType;

    private OX choseMultipleChoiceAnswer1;
    private OX choseMultipleChoiceAnswer2;
    private OX choseMultipleChoiceAnswer3;
    private OX choseMultipleChoiceAnswer4;
    private OX choseMultipleChoiceAnswer5;

    private OX choseOxAnswer;
    private String choseSubjectiveAnswer;

}
