package rimgosu.cbthub.domain.logs;

import lombok.*;
import rimgosu.cbthub.domain.Member.Member;
import rimgosu.cbthub.domain.round.Round;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoundLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_log_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = LAZY, cascade = ALL)
    private Round round;

    @OneToMany(mappedBy = "roundLog")
    private List<QuestionLog> questionLogs;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_log_id")
    private CategoryLog categoryLog;
}
