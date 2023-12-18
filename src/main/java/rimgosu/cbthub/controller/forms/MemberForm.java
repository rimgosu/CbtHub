package rimgosu.cbthub.controller.forms;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberForm {

    @NotEmpty(message = "회원 아이디는 필수입니다.")
    private String username;

    private String password;
    private String nickname;


}
