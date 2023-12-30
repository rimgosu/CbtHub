package rimgosu.cbthub.domain.round;

import lombok.*;
import rimgosu.cbthub.domain.category.Category;
import rimgosu.cbthub.domain.logs.RoundLog;
import rimgosu.cbthub.domain.question.Question;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String roundName;

    @Embedded
    private RoundInfo roundInfo;

    @OneToOne(mappedBy = "round", fetch = LAZY)
    private RoundLog roundLog;

    @OneToMany(mappedBy = "round", fetch = LAZY)
    private List<Question> questions;

    private int lastQuestionNumber;

    //==회차 등록==//
    public Round(String roundName, RoundInfo roundInfo, Category category) {
        this.roundName = roundName;
        this.roundInfo = roundInfo;
        this.category = category;
        this.lastQuestionNumber = 0;
    }

}
