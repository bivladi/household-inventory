package org.household.inventory.categories.service;

import java.util.Optional;
import java.util.UUID;
import org.household.inventory.model.Category;
import org.springframework.data.domain.Page;

public interface FindCategory {
  Optional<Category> findById(UUID id);

  Page<Category> findAllCategories(int page, int size, String sort, String direction);
}
