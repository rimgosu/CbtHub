package rimgosu.cbthub.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import rimgosu.cbthub.controller.forms.QuestionLogForm;
import rimgosu.cbthub.domain.logs.QuestionLog;
import rimgosu.cbthub.domain.logs.QuestionLogType;
import rimgosu.cbthub.domain.logs.RoundLog;
import rimgosu.cbthub.domain.question.MultipleChoiceAnswers;
import rimgosu.cbthub.domain.question.Question;
import rimgosu.cbthub.domain.question.QuestionType;
import rimgosu.cbthub.domain.round.Round;
import rimgosu.cbthub.service.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class QuestionLogController {

    private final QuestionService questionService;
    private final QuestionLogService questionLogService;
    private final MemberService memberService;
    private final RoundLogService roundLogService;

    @PostMapping("/questionLog/submit")
    public String answerSubmit(@Valid QuestionLogForm form, Model model, Authentication authentication) {
        /**
         * 1. 일단 객관식 먼저 만들어 놓은 것임 TODO: 주관식, OX 문제에 대해서도 적용할 것
         * 2. 문제의 답과 사용자가 만든 답이 일치하면 정답처리
         */

        log.info("PostMapping {questionId}/question/");

        Question findQuestion = questionService.findOne(form.getQuestionId());
        Round round = findQuestion.getRound();

        MultipleChoiceAnswers choseMultipleChoiceAnswers = new MultipleChoiceAnswers(
            form.getChoseMultipleChoiceAnswer1(),form.getChoseMultipleChoiceAnswer2(),form.getChoseMultipleChoiceAnswer3(),form.getChoseMultipleChoiceAnswer4(),form.getChoseMultipleChoiceAnswer5()
        );

        /**
         * 객관식 문제 처리
         */
        if (form.getQuestionType() == QuestionType.MULTIPLE_CHOICE) {
            /**
             * 정답 확인
             */
            QuestionLogType questionLogType = questionLogService.checkCorrect(findQuestion.getMultipleChoiceAnswers(), choseMultipleChoiceAnswers);

            /**
             * QuestionLog가 없을 때 : QuestionLog 등록
             * QuestionLog가 있을 때 : QuestionLog 수정
             */
            QuestionLog findQuestionLog = questionLogService.findByMemberUsername(authentication.getName());

            if (findQuestionLog == null) {
                QuestionLog questionLog = new QuestionLog(
                        memberService.findByUsername(authentication.getName()).get(0),  ,findQuestion, questionLogType, choseMultipleChoiceAnswers,
                        form.getChoseOxAnswer(), form.getChoseSubjectiveAnswer()
                );
                questionLogService.register(questionLog);
                model.addAttribute("questionLog", questionLog);
            } else {
                findQuestionLog.updateQuestionLog(questionLogType, choseMultipleChoiceAnswers, form.getChoseOxAnswer(), form.getChoseSubjectiveAnswer());
                model.addAttribute("questionLog", findQuestionLog);
                questionLogService.register(findQuestionLog);
            }

            /**
             * RoundLog가 없을 때 : RoundLog 등록
             * RoundLog가 있을 때 : RoundLog 수정
             */
            RoundLog findRoundLog = roundLogService.findByMemberUsername(authentication.getName());

            if (findRoundLog == null) {
                RoundLog roundLog = new RoundLog(
                        memberService.findByUsername(authentication.getName()).get(0), findQuestion, questionLogType, choseMultipleChoiceAnswers,
                        form.getChoseOxAnswer(), form.getChoseSubjectiveAnswer()
                );
                questionLogService.register(questionLog);
            } else {
                findQuestionLog.updateQuestionLog(questionLogType, choseMultipleChoiceAnswers, form.getChoseOxAnswer(), form.getChoseSubjectiveAnswer());
            }

            model.addAttribute("round", round);
            model.addAttribute("question", findQuestion);


            return "question/questionOneList";
        }

        System.out.println("객관식 외의 다른 문항도 구현해야합니다.");
        return null;

    }
}
