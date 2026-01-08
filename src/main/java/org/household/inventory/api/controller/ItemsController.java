package org.household.inventory.api.controller;

import java.util.List;
import org.household.inventory.api.dto.CreateItemRequest;
import org.household.inventory.api.dto.CreateItemResponse;
import org.household.inventory.api.dto.ItemResponse;
import org.household.inventory.api.mappers.ItemsMapper;
import org.household.inventory.domain.service.ItemsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemsController {

  private final ItemsMapper mapper;
  private final ItemsService service;

  public ItemsController(ItemsMapper mapper, ItemsService service) {
    this.mapper = mapper;
    this.service = service;
  }

  @PostMapping
  public CreateItemResponse create(@RequestBody CreateItemRequest request) {
    return mapper.toCreateResponse(service.create(mapper.toEntity(request)));
  }

  @GetMapping
  public List<ItemResponse> findAll() {
    return mapper.toResponseList(service.findAll());
  }

  @GetMapping("/{id}")
  public ItemResponse getById(@PathVariable(name = "id") String id) {
    return service
        .findById(java.util.UUID.fromString(id))
        .map(mapper::toResponse)
        .orElseThrow(() -> new RuntimeException("Item not found"));
  }
}
