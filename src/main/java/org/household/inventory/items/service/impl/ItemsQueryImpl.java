package org.household.inventory.items.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;
import org.household.inventory.items.repository.ItemsRepository;
import org.household.inventory.items.service.ItemsQuery;
import org.household.inventory.model.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemsQueryImpl implements ItemsQuery {
  private final ItemsRepository repository;

  public ItemsQueryImpl(ItemsRepository repository) {
    this.repository = repository;
  }

  public Optional<Item> findById(UUID id) {
    return repository.findById(id);
  }

  public List<Item> findAll() {
    return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
  }
}
