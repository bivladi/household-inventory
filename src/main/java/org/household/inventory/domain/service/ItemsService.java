package org.household.inventory.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;
import org.household.inventory.domain.entity.Item;
import org.household.inventory.domain.repository.ItemsRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemsService {
  private final ItemsRepository repository;

  public ItemsService(ItemsRepository repository) {
    this.repository = repository;
  }

  public Item create(Item item) {
    return repository.save(item);
  }

  public Optional<Item> findById(UUID id) {
    return repository.findById(id);
  }

  public List<Item> findAll() {
    return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
  }
}
