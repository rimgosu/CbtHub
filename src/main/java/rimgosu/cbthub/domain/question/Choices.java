package rimgosu.cbthub.domain.question;


import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Choices {
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String choice5;
}
