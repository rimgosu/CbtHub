package rimgosu.cbthub.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import rimgosu.cbthub.config.CustomUser;
import rimgosu.cbthub.controller.forms.QuestionForm;
import rimgosu.cbthub.controller.forms.QuestionLogForm;
import rimgosu.cbthub.domain.logs.QuestionLog;
import rimgosu.cbthub.service.QuestionLogService;
import rimgosu.cbthub.service.QuestionService;
import rimgosu.cbthub.service.RoundService;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class QuestionLogController {

    private final RoundService roundService;
    private final QuestionService questionService;
    private final QuestionLogService questionLogService;

    @PostMapping("/questionLog/submit")
    public String answerSubmit(@Valid QuestionLogForm form, Model model, Authentication authentication) {
        log.info("PostMapping {questionId}/question/");
        System.out.println("form.toString() = " + form.toString());

        String username =  authentication.getName();
        System.out.println("username = " + username);

        

        return "question/questionOneList";
    }
}
