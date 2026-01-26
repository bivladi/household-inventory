package org.household.inventory.categories.application.impl;

import java.util.UUID;
import org.household.inventory.categories.application.CategoriesApplicationService;
import org.household.inventory.categories.repository.CategoriesRepository;
import org.household.inventory.categories.service.CategoriesQuery;
import org.household.inventory.categories.service.impl.CategoriesQueryImpl;
import org.household.inventory.common.exception.BadArgumentApplicationException;
import org.household.inventory.common.exception.NotFoundApplicationException;
import org.household.inventory.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CategoriesApplicationServiceImpl implements CategoriesApplicationService {

  private final CategoriesQuery categoriesQuery;
  private final CategoriesRepository repository;

  public CategoriesApplicationServiceImpl(
      CategoriesQuery categoriesQuery, CategoriesRepository repository) {
    this.categoriesQuery = categoriesQuery;
    this.repository = repository;
  }

  @Override
  public Category createCategory(Category category) {
    return repository.save(category);
  }

  @Override
  public Category getCategoryById(String id) {
    if (!StringUtils.hasText(id)) {
      throw new BadArgumentApplicationException("category id cannot be null or empty");
    }
    return ((CategoriesQueryImpl) categoriesQuery)
        .findById(UUID.fromString(id))
        .orElseThrow(() -> new NotFoundApplicationException("category not found with id: " + id));
  }

  @Override
  public Page<Category> getAllCategories(int page, int size, String sort, String direction) {
    return categoriesQuery.findAllCategories(page, size, sort, direction);
  }

  @Override
  public Category updateCategory(String id, Category updateRequest) {
    if (!StringUtils.hasText(id)) {
      throw new BadArgumentApplicationException("category id cannot be null or empty");
    }

    Category existingCategory =
        ((CategoriesQueryImpl) categoriesQuery)
            .findById(UUID.fromString(id))
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
  public void deleteCategory(String id) {
    repository.deleteById(UUID.fromString(id));
  }
}
