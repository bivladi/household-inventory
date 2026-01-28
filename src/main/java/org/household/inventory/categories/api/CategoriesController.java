package org.household.inventory.categories.api;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.household.inventory.categories.application.CategoriesApplicationService;
import org.household.inventory.categories.dto.CategoryResponse;
import org.household.inventory.categories.dto.CreateCategoryRequest;
import org.household.inventory.categories.dto.CreateCategoryResponse;
import org.household.inventory.categories.dto.UpdateCategoryRequest;
import org.household.inventory.categories.dto.UpdateCategoryResponse;
import org.household.inventory.categories.mappers.CategoriesMapper;
import org.household.inventory.common.api.model.PageNumber;
import org.household.inventory.common.api.model.PaginatedResponse;
import org.household.inventory.common.api.model.SizeNumber;
import org.household.inventory.common.api.model.SortDirection;
import org.household.inventory.common.api.model.SortField;
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
@RequestMapping("/api/categories")
public class CategoriesController {

  private final CategoriesMapper mapper;
  private final CategoriesApplicationService service;

  public CategoriesController(CategoriesMapper mapper, CategoriesApplicationService service) {
    this.mapper = mapper;
    this.service = service;
  }

  @PostMapping
  public CreateCategoryResponse create(@RequestBody CreateCategoryRequest request) {
    return mapper.toCreateResponse(service.createCategory(mapper.toEntity(request)));
  }

  @GetMapping
  public PaginatedResponse<CategoryResponse> findAll(
      @Parameter(schema = @Schema(type = "integer", defaultValue = "0", minimum = "0"))
          @RequestParam(defaultValue = "0")
          PageNumber page,
      @Parameter(schema = @Schema(type = "integer", defaultValue = "10", minimum = "0"))
          @RequestParam(defaultValue = "10")
          SizeNumber size,
      @RequestParam(defaultValue = "CREATED_AT") SortField sort,
      @RequestParam(defaultValue = "ASC") SortDirection direction) {
    return mapper.toPaginatedResponse(
        service.getAllCategories(
            page.value(), size.value(), sort.getValue(), direction.getValue()));
  }

  @GetMapping("/{id}")
  public CategoryResponse getById(@PathVariable(name = "id") UUID id) {
    return mapper.toResponse(service.getCategoryById(id));
  }

  @PutMapping("/{id}")
  public UpdateCategoryResponse update(
      @PathVariable(name = "id") UUID id, @RequestBody UpdateCategoryRequest request) {
    return mapper.toUpdateResponse(service.updateCategory(id, mapper.toUpdateEntity(request)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable(name = "id") UUID id) {
    service.deleteCategory(id);
    return ResponseEntity.ok().build();
  }
}
