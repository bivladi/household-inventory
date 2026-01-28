package org.household.inventory.items.service.impl;

import org.household.inventory.items.repository.ItemsRepository;
import org.household.inventory.items.service.CreateItem;
import org.household.inventory.model.Item;
import org.springframework.stereotype.Service;

@Service
public class CreateItemImpl implements CreateItem {
  private final ItemsRepository repository;

  public CreateItemImpl(ItemsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Item saveEntity(Item entity) {
    return repository.save(entity);
  }
}
