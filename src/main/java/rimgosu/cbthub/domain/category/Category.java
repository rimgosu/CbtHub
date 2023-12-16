package rimgosu.cbthub.domain.category;


import lombok.*;
import rimgosu.cbthub.domain.board.BoardComment;
import rimgosu.cbthub.domain.logs.CategoryLog;
import rimgosu.cbthub.domain.round.Round;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String categoryName;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Embedded
    private CategoryInfo categoryInfo;

    @OneToOne(mappedBy = "category", fetch = LAZY)
    private CategoryLog categoryLog;

    @OneToMany(mappedBy = "category")
    private List<BoardComment> boardComments;

    @OneToMany(mappedBy = "category")
    private List<Round> rounds;

    //== 카테고리 등록 ==//
    public Category(String categoryName, CategoryType categoryType, CategoryInfo categoryInfo) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.categoryInfo = categoryInfo;
    }


}
