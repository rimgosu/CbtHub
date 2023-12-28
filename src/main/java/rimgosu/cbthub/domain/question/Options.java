package rimgosu.cbthub.domain.question;


import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Options {
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
}
