package rimgosu.cbthub.domain.Member;

import lombok.*;
import rimgosu.cbthub.domain.board.BoardPost;
import rimgosu.cbthub.domain.board.BoardComment;
import rimgosu.cbthub.domain.board.QuestionComment;
import rimgosu.cbthub.domain.logs.CategoryLog;
import rimgosu.cbthub.domain.logs.QuestionLog;
import rimgosu.cbthub.domain.logs.RoundLog;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String nickname;
    private Boolean isActivate;

    @Enumerated(EnumType.STRING)
    private MemberGrade grade;

    private Date registerDate;
    private int point;

    @OneToOne(mappedBy = "member", fetch = LAZY) private QuestionLog questionLogs;
    @OneToOne(mappedBy = "member", fetch = LAZY) private RoundLog roundLogs;
    @OneToOne(mappedBy = "member", fetch = LAZY) private CategoryLog categoryLogs;
    @OneToMany(mappedBy = "member") private List<QuestionComment> questionComments;
    @OneToMany(mappedBy = "member") private List<BoardComment> boardComments;
    @OneToMany(mappedBy = "member") private List<BoardPost> boardPosts;


    //== 회원가입 ==//
    public Member(String nickname, String password, String username) {
        this.nickname = nickname;
        this.password = password;
        this.username = username;
        this.grade = MemberGrade.NORMAL; // 기본값 설정
        this.point = 0; // 기본값 설정
        this.role = Role.USER; // 기본값 설정
        this.isActivate = true; // 기본값 설정
        this.registerDate = new Date(); // 현재 시간으로 설정
    }

}
