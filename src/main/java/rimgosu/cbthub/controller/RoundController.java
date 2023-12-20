package rimgosu.cbthub.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rimgosu.cbthub.controller.forms.RoundForm;
import rimgosu.cbthub.domain.category.Category;
import rimgosu.cbthub.domain.round.Round;
import rimgosu.cbthub.service.CategoryService;
import rimgosu.cbthub.service.RoundService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RoundController {

    private final RoundService roundService;
    private final CategoryService categoryService;

    @GetMapping("{categoryId}/round/")
    public String list(@PathVariable Long categoryId, Model model) {
        log.info("GetMapping {categoryId}/list");
        List<Round> rounds = roundService.findByCategoryId(categoryId);
        Category category = categoryService.findOne(categoryId);
        model.addAttribute("rounds", rounds);
        model.addAttribute("category", category);
        return "round/roundList";

    }

    @GetMapping("{categoryId}/round/new")
    public String createForm(@PathVariable Long categoryId, Model model) {
        log.info("GetMapping {categoryId}/new");
        Category category = categoryService.findOne(categoryId);
        model.addAttribute("roundForm", new RoundForm());
        model.addAttribute("category", category);

        return "round/createRoundForm";
    }

    @PostMapping("{categoryId}/round/new")
    public String create(@PathVariable Long categoryId ,@Valid RoundForm form, BindingResult result) {
        log.info("PostMapping /new");

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                System.out.println(error.getDefaultMessage());
            });
            return "round/createroundForm";
        }

        Category category = categoryService.findOne(categoryId);

        Round round = new Round(form.getRoundName(), form.getRoundInfo(), category);
        roundService.register(round);

        return "redirect:/"+categoryId+"/round/";
    }


}
