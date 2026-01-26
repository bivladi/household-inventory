package org.household.inventory.categories.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;
import org.household.inventory.categories.repository.CategoriesRepository;
import org.household.inventory.categories.service.CategoriesQuery;
import org.household.inventory.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoriesQueryImpl implements CategoriesQuery {
  private final CategoriesRepository repository;

  public CategoriesQueryImpl(CategoriesRepository repository) {
    this.repository = repository;
  }

  public Optional<Category> findById(UUID id) {
    return repository.findById(id);
  }

  public List<Category> findAll() {
    return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
  }

  @Override
  public Page<Category> findAllCategories(int page, int size, String sort, String direction) {
    Sort.Direction directionValue = Sort.Direction.fromString(direction);
    Pageable pageable = PageRequest.of(page, size, Sort.by(directionValue, sort));
    return repository.findAll(pageable);
  }
}
