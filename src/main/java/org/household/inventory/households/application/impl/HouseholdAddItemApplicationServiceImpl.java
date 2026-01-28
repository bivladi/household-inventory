package org.household.inventory.households.application.impl;

import jakarta.transaction.Transactional;
import java.util.Objects;
import java.util.UUID;
import org.household.inventory.categories.service.FindCategory;
import org.household.inventory.common.exception.BadArgumentApplicationException;
import org.household.inventory.common.exception.NotFoundApplicationException;
import org.household.inventory.households.application.HouseholdAddItemApplicationService;
import org.household.inventory.households.dto.CreateItemRequest;
import org.household.inventory.households.repository.HouseholdItemRepository;
import org.household.inventory.households.repository.HouseholdsRepository;
import org.household.inventory.items.service.CreateItem;
import org.household.inventory.items.service.FindItem;
import org.household.inventory.model.Household;
import org.household.inventory.model.HouseholdItem;
import org.household.inventory.model.Item;
import org.springframework.stereotype.Service;

@Service
public class HouseholdAddItemApplicationServiceImpl implements HouseholdAddItemApplicationService {
  private final HouseholdsRepository repository;
  private final HouseholdItemRepository householdItemRepository;
  private final CreateItem itemCreator;
  private final FindItem itemFinder;
  private final FindCategory categoryFinder;

  public HouseholdAddItemApplicationServiceImpl(
      HouseholdsRepository repository,
      HouseholdItemRepository householdItemRepository,
      CreateItem itemCreator,
      FindItem itemFinder,
      FindCategory categoryFinder) {
    this.repository = repository;
    this.householdItemRepository = householdItemRepository;
    this.itemCreator = itemCreator;
    this.itemFinder = itemFinder;
    this.categoryFinder = categoryFinder;
  }

  @Override
  @Transactional
  public Item createItem(UUID householdId, CreateItemRequest request) {
    var household =
        repository
            .findById(householdId)
            .orElseThrow(
                () ->
                    new NotFoundApplicationException(
                        String.format("Household with id=%s isnot found", householdId)));
    if (itemFinder.existsByName(request.getName())) {
      throw new BadArgumentApplicationException(
          String.format("Item with id=%s already esists", request.getName()));
    }
    final var item = itemCreator.saveEntity(getItem(request));
    saveHouseholdItem(household, item, request.getAmount());
    // link to category
    if (Objects.nonNull(request.getCategoryId())) {
      updateCategories(request.getCategoryId(), item);
    }
    return item;
  }

  private void updateCategories(UUID categoryId, Item item) {
    final var category =
        categoryFinder
            .findById(categoryId)
            .orElseThrow(
                () ->
                    new NotFoundApplicationException(
                        String.format("Category with id=%s is not found", categoryId)));
    if (itemFinder.isUnderCategory(category.getId())) {
      throw new BadArgumentApplicationException(
          String.format(
              "Item id=%s is under category id=%s already", item.getId(), category.getId()));
    }
    item.getCategories().add(category);
  }

  private void saveHouseholdItem(Household household, Item item, Long amount) {
    householdItemRepository.save(new HouseholdItem(household, item, amount));
  }

  private static Item getItem(CreateItemRequest request) {
    final var item = new Item();
    item.setName(request.getName());
    item.setDescription(request.getDescription());
    item.setPrice(request.getPrice());
    return item;
  }
}
