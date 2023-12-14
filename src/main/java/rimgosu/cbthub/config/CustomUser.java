package rimgosu.cbthub.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import rimgosu.cbthub.domain.Member.Member;


@Getter @Setter @ToString
public class CustomUser extends User {

    private Member member;

    public CustomUser(Member member) {
        super(member.getUsername(), member.getPassword(), AuthorityUtils.createAuthorityList("ROLE_"+member.getRole().toString()));

        this.member = member;

    }

}