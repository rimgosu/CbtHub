package rimgosu.cbthub.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import rimgosu.cbthub.controller.forms.CategoryForm;
import rimgosu.cbthub.domain.category.Category;
import rimgosu.cbthub.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public String list(Model model) {
        List<Category> categories = categoryService.findMembers();
        model.addAttribute("categories", categories);
        return "category/categoryList";

    }

    @GetMapping("/category/new")
    public String createForm(Model model) {
        log.info("GetMapping /new");
        model.addAttribute("categoryForm", new CategoryForm());
        return "category/createCategoryForm";
    }

    @PostMapping("/category/new")
    public String create(@Valid CategoryForm form, BindingResult result) {
        log.info("PostMapping /new");

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                System.out.println(error.getDefaultMessage());
            });
            return "category/createCategoryForm";
        }

        Category category = new Category(form.getCategoryName(), form.getCategoryType(), form.getCategoryInfo());
        categoryService.register(category);

        return "redirect:/";
    }


}
