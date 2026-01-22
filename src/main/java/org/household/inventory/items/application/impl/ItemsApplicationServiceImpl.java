package org.household.inventory.items.application.impl;

import java.util.List;
import java.util.UUID;
import org.household.inventory.items.application.ItemsApplicationService;
import org.household.inventory.items.exception.BadArgumentApplicationException;
import org.household.inventory.items.exception.ItemNotFoundApplicationException;
import org.household.inventory.items.repository.ItemsRepository;
import org.household.inventory.items.service.ItemsQuery;
import org.household.inventory.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ItemsApplicationServiceImpl implements ItemsApplicationService {

  private final ItemsQuery itemsQuery;
  private final ItemsRepository repository;

  public ItemsApplicationServiceImpl(ItemsQuery itemsQuery, ItemsRepository repository) {
    this.itemsQuery = itemsQuery;
    this.repository = repository;
  }

  @Override
  public Item createItem(Item item) {
    return repository.save(item);
  }

  @Override
  public Item getItemById(String id) {
    if (!StringUtils.hasText(id)) {
      throw new BadArgumentApplicationException("item id cannot be null or empty");
    }
    return itemsQuery
        .findById(UUID.fromString(id))
        .orElseThrow(() -> new ItemNotFoundApplicationException("item not found with id: " + id));
  }

  @Override
  public List<Item> getAllItems() {
    return itemsQuery.findAll();
  }

  @Override
  public Page<Item> getAllItems(int page, int size, String sort, String direction) {
    return itemsQuery.findAll(page, size, sort, direction);
  }

  @Override
  public Item updateItem(String id, Item updateRequest) {
    if (!StringUtils.hasText(id)) {
      throw new BadArgumentApplicationException("item id cannot be null or empty");
    }

    Item existingItem =
        itemsQuery
            .findById(UUID.fromString(id))
            .orElseThrow(
                () -> new ItemNotFoundApplicationException("item not found with id: " + id));

    // Apply partial updates - only update non-null fields
    if (updateRequest.getName() != null) {
      existingItem.setName(updateRequest.getName());
    }
    if (updateRequest.getDescription() != null) {
      existingItem.setDescription(updateRequest.getDescription());
    }
    if (updateRequest.getAmount() != null) {
      existingItem.setAmount(updateRequest.getAmount());
    }
    if (updateRequest.getPrice() != null) {
      existingItem.setPrice(updateRequest.getPrice());
    }

    return repository.save(existingItem);
  }
}
