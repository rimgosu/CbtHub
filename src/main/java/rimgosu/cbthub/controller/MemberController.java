package rimgosu.cbthub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rimgosu.cbthub.controller.forms.MemberForm;
import rimgosu.cbthub.controller.forms.LoginForm;
import rimgosu.cbthub.domain.Member.Member;
import rimgosu.cbthub.service.MemberService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/members/*")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

        @Autowired PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Member member = new Member(form.getNickname(), passwordEncoder.encode(form.getPassword()), form.getUsername());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid LoginForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "members/loginForm";
        }

        List<Member> byUsernamePassword = memberService.findByUsernamePassword(form.getUsername(), passwordEncoder.encode(form.getPassword()));

        if (byUsernamePassword.isEmpty()) {
            return "redirect:/login";
        } else {
            return "redirect:/";
        }


    }

}
