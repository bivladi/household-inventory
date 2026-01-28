package org.household.inventory.categories.application.impl;

import java.util.UUID;
import org.household.inventory.categories.application.CategoriesApplicationService;
import org.household.inventory.categories.repository.CategoriesRepository;
import org.household.inventory.categories.service.FindCategory;
import org.household.inventory.categories.service.impl.FindCategoryImpl;
import org.household.inventory.common.exception.NotFoundApplicationException;
import org.household.inventory.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CategoriesApplicationServiceImpl implements CategoriesApplicationService {

  private final FindCategory findCategory;
  private final CategoriesRepository repository;

  public CategoriesApplicationServiceImpl(
      FindCategory findCategory, CategoriesRepository repository) {
    this.findCategory = findCategory;
    this.repository = repository;
  }

  @Override
  public Category createCategory(Category category) {
    return repository.save(category);
  }

  @Override
  public Category getCategoryById(UUID id) {
    return ((FindCategoryImpl) findCategory)
        .findById(id)
        .orElseThrow(() -> new NotFoundApplicationException("category not found with id: " + id));
  }

  @Override
  public Page<Category> getAllCategories(int page, int size, String sort, String direction) {
    return findCategory.findAllCategories(page, size, sort, direction);
  }

  @Override
  public Category updateCategory(UUID id, Category updateRequest) {
    Category existingCategory =
        ((FindCategoryImpl) findCategory)
            .findById(id)
            .orElseThrow(
                () -> new NotFoundApplicationException("category not found with id: " + id));

    // Apply partial updates - only update non-null fields
    if (updateRequest.getName() != null) {
      existingCategory.setName(updateRequest.getName());
    }
    if (updateRequest.getDescription() != null) {
      existingCategory.setDescription(updateRequest.getDescription());
    }

    return repository.save(existingCategory);
  }

  @Override
  public void deleteCategory(UUID id) {
    repository.deleteById(id);
  }
}
