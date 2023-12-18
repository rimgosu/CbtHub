package rimgosu.cbthub.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rimgosu.cbthub.domain.category.Category;
import rimgosu.cbthub.domain.round.Round;
import rimgosu.cbthub.service.CategoryService;
import rimgosu.cbthub.service.RoundService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/round/*")
@RequiredArgsConstructor
public class RoundController {

    private final RoundService roundService;
    private final CategoryService categoryService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Round> rounds = roundService.findMembers();
        model.addAttribute("rounds", rounds);
        return "round/roundList";

    }

    @GetMapping("{categoryId}/new")
    public String createForm(@PathVariable Long categoryId, Model model) {
        log.info("GetMapping {categoryId}/new");
        Category category = categoryService.findOne(categoryId);
        model.addAttribute("roundForm", new RoundForm());
        model.addAttribute("category", category);

        return "round/createRoundForm";
    }

    @PostMapping("/new")
    public String create(@Valid RoundForm form, BindingResult result) {
        log.info("PostMapping /new");

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                System.out.println(error.getDefaultMessage());
            });
            return "round/createroundForm";
        }

        Round round = new Round(form.getRoundName(), form.getRoundInfo(), form.getCategory());
        roundService.register(round);

        return "redirect:/";
    }


}
