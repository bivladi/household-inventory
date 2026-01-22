package org.household.inventory.items.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;
import org.household.inventory.items.repository.ItemsRepository;
import org.household.inventory.items.service.ItemsQuery;
import org.household.inventory.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ItemsQueryImpl implements ItemsQuery {
  private final ItemsRepository repository;

  public ItemsQueryImpl(ItemsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<Item> findById(UUID id) {
    return repository.findById(id);
  }

  @Override
  public List<Item> findAll() {
    return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
  }

  @Override
  public Page<Item> findAll(int page, int size, String sort, String direction) {
    Sort.Direction directionValue = Sort.Direction.fromString(direction);
    Pageable pageable = PageRequest.of(page, size, Sort.by(directionValue, sort));
    return repository.findAll(pageable);
  }
}
