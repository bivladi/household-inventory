package org.household.inventory.categories.service.impl;

import java.util.Optional;
import java.util.UUID;
import org.household.inventory.categories.repository.CategoriesRepository;
import org.household.inventory.categories.service.FindCategory;
import org.household.inventory.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FindCategoryImpl implements FindCategory {
  private final CategoriesRepository repository;

  public FindCategoryImpl(CategoriesRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<Category> findById(UUID id) {
    return repository.findById(id);
  }

  @Override
  public Page<Category> findAllCategories(int page, int size, String sort, String direction) {
    Sort.Direction directionValue = Sort.Direction.fromString(direction);
    Pageable pageable = PageRequest.of(page, size, Sort.by(directionValue, sort));
    return repository.findAll(pageable);
  }
}
