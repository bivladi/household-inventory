package org.household.inventory.categories.application;

import org.household.inventory.model.Category;
import org.springframework.data.domain.Page;

public interface CategoriesApplicationService {
  Category createCategory(Category request);

  Category getCategoryById(String id);

  Page<Category> getAllCategories(int page, int size, String sort, String direction);

  Category updateCategory(String id, Category updateRequest);

  void deleteCategory(String id);
}
