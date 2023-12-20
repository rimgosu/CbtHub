package rimgosu.cbthub.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import rimgosu.cbthub.controller.forms.QuestionForm;
import rimgosu.cbthub.domain.question.Question;
import rimgosu.cbthub.domain.round.Round;
import rimgosu.cbthub.service.QuestionService;
import rimgosu.cbthub.service.RoundService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class QuestionController {

    private final RoundService roundService;
    private final QuestionService questionService;

    @GetMapping("{roundId}/question/")
    public String list(@PathVariable Long roundId, Model model) {
        log.info("GetMapping {roundId}/question/");
        List<Question> questions = questionService.findByRoundId(roundId);
        Round round = roundService.findOne(roundId);
        model.addAttribute("questions", questions);
        model.addAttribute("round", round);
        return "question/questionList";

    }

    @GetMapping("{roundId}/question/new")
    public String createForm(@PathVariable Long roundId, Model model) {
        log.info("GetMapping {roundId}/question/new");
        Round round = roundService.findOne(roundId);
        model.addAttribute("questionForm", new QuestionForm());
        model.addAttribute("round", round);

        return "question/createQuestionForm";
    }

    @PostMapping("{roundId}/question/new")
    public String create(@PathVariable Long roundId ,@Valid QuestionForm form, BindingResult result) {
        log.info("PostMapping {roundId}/question/new");

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                System.out.println(error.getDefaultMessage());
            });
            return "question/createQuestionForm";
        }

        Round round = roundService.findOne(roundId);

        Question question = new Question(form.getNumber(), form.getQuestionType(), form.getWhatQuestion(), form.getPhoto(), form.getOptions(),
                form.getChoices(), form.getIsO(), form.getMultipleChoiceAnswer(), form.getSubjectiveAnswer(), form.getCommentary(), form.getGptCommentary(), round);
        questionService.register(question);

        return "redirect:/"+roundId+"/question/";
    }


}
