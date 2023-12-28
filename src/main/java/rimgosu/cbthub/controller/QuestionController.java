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
import rimgosu.cbthub.domain.question.*;
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

        QuestionForm questionForm = new QuestionForm();

        int lastQuestionNumber = round.getLastQuestionNumber();
        questionForm.setNumber(lastQuestionNumber+1);

        model.addAttribute("questionForm", questionForm);
        model.addAttribute("round", round);


        return "question/createQuestionForm";
    }

    @PostMapping("{roundId}/question/new")
    public String create(@PathVariable Long roundId ,@Valid QuestionForm form, BindingResult result) {
        log.info("PostMapping {roundId}/question/new");
        System.out.println("form.toString() = " + form.toString());

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                System.out.println(error.getDefaultMessage());
            });
            return "question/createQuestionForm";
        }

        Round round = roundService.findOne(roundId);
        Question question = getQuestion(form, round);
        questionService.register(question);
        roundService.plusOneLastNumber(roundId, form.getNumber());

        return "redirect:/"+roundId+"/question/";
    }

    private static Question getQuestion(QuestionForm form, Round round) {
        Options options = new Options(form.getOption1(), form.getOption2(), form.getOption3(), form.getOption4(), form.getOption5());
        Choices choices = new Choices(form.getChoice1(), form.getChoice2(), form.getChoice3(), form.getChoice4(), form.getChoice5());
        MultipleChoiceAnswers multipleChoiceAnswers = new MultipleChoiceAnswers(form.getMultipleChoiceAnswer1(), form.getMultipleChoiceAnswer2(), form.getMultipleChoiceAnswer3(), form.getMultipleChoiceAnswer4(), form.getMultipleChoiceAnswer5());

        return new Question(form.getNumber(), form.getQuestionType(), form.getWhatQuestion(), form.getPhoto(),
                options, choices, form.getOxChoiceAnswer(), multipleChoiceAnswers, form.getSubjectiveAnswer(), form.getCommentary(),
                form.getGptCommentary(), round);
    }

    @GetMapping("/question/{questionId}")
    public String oneList(@PathVariable Long questionId, Model model) {
        log.info("GetMapping {questionId}/question/");
        Question question = questionService.findOne(questionId);
        Round round = question.getRound();
        model.addAttribute("question", question);
        model.addAttribute("round", round);
        return "question/questionOneList";
    }

    @PostMapping("/question/submit")
    public String answerSubmit(@Valid QuestionForm form, Model model) {
        log.info("PostMapping {questionId}/question/");
        System.out.println("form = " + form.toString());
        return "question/questionOneList";
    }


}
