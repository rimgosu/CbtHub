package rimgosu.cbthub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rimgosu.cbthub.domain.category.Category;
import rimgosu.cbthub.repository.CategoryRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * 카테고리 등록
     */
    @Transactional
    public Long register(Category category) {
        validataDuplicateCategory(category); // 중복회원 검증
        categoryRepository.save(category);
        return category.getId();
    }

    private void validataDuplicateCategory(Category category) {
        // EXCEPTION
        List<Category> findMembers = categoryRepository.findByName(category.getCategoryName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 카테고리입니다.");
        }
    }

    public List<Category> findMembers() {
        return categoryRepository.findAll();
    }

    public Category findOne(Long memberId) {
        return categoryRepository.findOne(memberId);
    }

    public List<Category> findByName(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }

}
