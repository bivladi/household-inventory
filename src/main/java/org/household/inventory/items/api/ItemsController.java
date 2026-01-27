package org.household.inventory.items.api;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.household.inventory.common.api.model.PageNumber;
import org.household.inventory.common.api.model.PaginatedResponse;
import org.household.inventory.common.api.model.SizeNumber;
import org.household.inventory.common.api.model.SortDirection;
import org.household.inventory.common.api.model.SortField;
import org.household.inventory.items.application.ItemsApplicationService;
import org.household.inventory.items.dto.CreateItemRequest;
import org.household.inventory.items.dto.CreateItemResponse;
import org.household.inventory.items.dto.ItemResponse;
import org.household.inventory.items.dto.UpdateItemRequest;
import org.household.inventory.items.dto.UpdateItemResponse;
import org.household.inventory.items.mappers.ItemsMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
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
  public PaginatedResponse<ItemResponse> findAll(
      @Parameter(schema = @Schema(type = "integer", defaultValue = "0", minimum = "0"))
          @RequestParam(defaultValue = "0")
          PageNumber page,
      @Parameter(schema = @Schema(type = "integer", defaultValue = "10", minimum = "0"))
          @RequestParam(defaultValue = "10")
          SizeNumber size,
      @RequestParam(defaultValue = "CREATED_AT") SortField sort,
      @RequestParam(defaultValue = "ASC") SortDirection direction) {
    return mapper.toPaginatedResponse(
        service.getAllItems(page.value(), size.value(), sort.getValue(), direction.getValue()));
  }

  @GetMapping("/{id}")
  public ItemResponse getById(@PathVariable(name = "id") String id) {
    return mapper.toResponse(service.getItemById(id));
  }

  @PutMapping("/{id}")
  public UpdateItemResponse update(
      @PathVariable(name = "id") String id, @RequestBody UpdateItemRequest request) {
    return mapper.toUpdateResponse(service.updateItem(id, mapper.toUpdateEntity(request)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable(name = "id") String id) {
    service.deleteItem(id);
    return ResponseEntity.ok().build();
  }
}
