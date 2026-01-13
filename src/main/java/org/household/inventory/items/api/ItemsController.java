package org.household.inventory.items.api;

import org.household.inventory.items.application.ItemsApplicationService;
import org.household.inventory.items.dto.CreateItemRequest;
import org.household.inventory.items.dto.CreateItemResponse;
import org.household.inventory.items.dto.ItemResponse;
import org.household.inventory.items.mappers.ItemsMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {

  private final ItemsMapper mapper;
  private final ItemsApplicationService service;

  public ItemsController(ItemsMapper mapper, ItemsApplicationService service) {
    this.mapper = mapper;
    this.service = service;
  }

  @PostMapping
  public CreateItemResponse create(@RequestBody CreateItemRequest request) {
    return mapper.toCreateResponse(service.createItem(mapper.toEntity(request)));
  }

  @GetMapping
  public List<ItemResponse> findAll() {
    return mapper.toResponseList(service.getAllItems());
  }

  @GetMapping("/{id}")
  public ItemResponse getById(@PathVariable(name = "id") String id) {
    return mapper.toResponse(service.getItemById(id));
  }
}
