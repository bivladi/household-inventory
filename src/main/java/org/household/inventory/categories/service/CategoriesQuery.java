package org.household.inventory.categories.service;

import org.household.inventory.model.Category;
import org.springframework.data.domain.Page;

@FunctionalInterface
public interface CategoriesQuery {
  Page<Category> findAllCategories(int page, int size, String sort, String direction);
}
