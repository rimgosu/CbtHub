package rimgosu.cbthub.domain.logs;

import lombok.*;
import rimgosu.cbthub.domain.Member.Member;
import rimgosu.cbthub.domain.category.Category;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class CategoryLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_log_id")
    private Long id;

    // 회원별 카테고리 진행도
    private float categoryProgressPercent;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    private Category category;

    @OneToMany(mappedBy = "categoryLog")
    private List<RoundLog> roundLogs;

}
