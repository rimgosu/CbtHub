package rimgosu.cbthub.controller;

import lombok.*;
import rimgosu.cbthub.domain.category.CategoryInfo;
import rimgosu.cbthub.domain.category.CategoryType;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryForm {

    @NotEmpty(message = "카테고리 이름은 필수입니다.")
    private String categoryName;

    private CategoryType categoryType;
    private CategoryInfo categoryInfo;


}
