package rimgosu.cbthub.controller.forms;

import lombok.*;
import rimgosu.cbthub.domain.question.QuestionType;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionForm {

    @NotEmpty(message = "문제 번호는 필수입니다.")
    private int number;
    private QuestionType questionType;
    private String whatQuestion;
    private String photo;
    private List<String> options;
    private List<String> choices;
    private Boolean isO;
    private int multipleChoiceAnswer;
    private String subjectiveAnswer;
    private String commentary;
    private String gptCommentary;
    private Long roundId;

}
