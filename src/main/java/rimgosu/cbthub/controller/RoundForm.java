package rimgosu.cbthub.controller;

import lombok.*;
import rimgosu.cbthub.domain.category.Category;
import rimgosu.cbthub.domain.round.RoundInfo;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoundForm {

    @NotEmpty(message = "라운드 이름은 필수입니다.")
    private String roundName;
    private RoundInfo roundInfo;
    private Category category;

}
