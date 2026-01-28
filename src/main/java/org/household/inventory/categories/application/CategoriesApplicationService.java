package org.household.inventory.categories.application;

import java.util.UUID;
import org.household.inventory.model.Category;
import org.springframework.data.domain.Page;

public interface CategoriesApplicationService {
  Category createCategory(Category request);

  Category getCategoryById(UUID id);

  Page<Category> getAllCategories(int page, int size, String sort, String direction);

  Category updateCategory(UUID id, Category updateRequest);

  void deleteCategory(UUID id);
}
