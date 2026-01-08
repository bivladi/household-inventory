package org.household.inventory.api.mappers;

import java.util.List;
import java.util.stream.Collectors;
import org.household.inventory.api.dto.CreateItemRequest;
import org.household.inventory.api.dto.CreateItemResponse;
import org.household.inventory.api.dto.ItemResponse;
import org.household.inventory.domain.entity.Item;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between Item entities and DTOs. Follows single responsibility
 * principle and provides clean separation between API layer and domain layer.
 */
@Component
public final class ItemsMapper {

  /**
   * Converts CreateItemRequest DTO to Item entity.
   *
   * @param request the DTO containing item creation data
   * @return the Item entity
   * @throws IllegalArgumentException if request is null
   */
  public Item toEntity(CreateItemRequest request) {
    if (request == null) {
      throw new IllegalArgumentException("CreateItemRequest cannot be null");
    }

    Item item = new Item();
    item.setName(request.getName());
    item.setDescription(request.getDescription());
    item.setAmount(request.getAmount());
    item.setPrice(request.getPrice());
    // Initialize with empty list to avoid nulls
    item.setCategories(List.of());

    return item;
  }

  /**
   * Converts Item entity to CreateItemResponse DTO.
   *
   * @param item the entity to convert
   * @return the DTO representation
   * @throws IllegalArgumentException if item is null
   */
  public CreateItemResponse toCreateResponse(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null");
    }

    return new CreateItemResponse(item.getId().toString(), item.getCreatedAt());
  }

  /**
   * Converts Item entity to ItemResponse DTO.
   *
   * @param item the entity to convert
   * @return the DTO representation
   * @throws IllegalArgumentException if item is null
   */
  public ItemResponse toResponse(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null");
    }

    return new ItemResponse(
        item.getId(),
        item.getName(),
        item.getDescription(),
        item.getAmount(),
        item.getPrice(),
        List.of(),
        //        item.getCategories() != null
        //            ?
        // item.getCategories().stream().map(Category::getName).collect(Collectors.toList())
        //            : List.of(),
        item.getCreatedAt(),
        item.getUpdatedAt());
  }

  public List<ItemResponse> toResponseList(List<Item> all) {
    return all.stream().map(this::toResponse).collect(Collectors.toList());
  }
}
