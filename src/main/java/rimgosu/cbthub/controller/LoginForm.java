package rimgosu.cbthub.controller;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginForm {

    @NotEmpty(message = "회원 아이디는 필수입니다.")
    private String username;
    private String password;

}
