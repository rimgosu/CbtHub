package rimgosu.cbthub.domain.question;


import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class MultipleChoiceAnswers {
    @Enumerated(EnumType.STRING)
    private OX multipleChoiceAnswer1;
    @Enumerated(EnumType.STRING)
    private OX multipleChoiceAnswer2;
    @Enumerated(EnumType.STRING)
    private OX multipleChoiceAnswer3;
    @Enumerated(EnumType.STRING)
    private OX multipleChoiceAnswer4;
    @Enumerated(EnumType.STRING)
    private OX multipleChoiceAnswer5;
}
