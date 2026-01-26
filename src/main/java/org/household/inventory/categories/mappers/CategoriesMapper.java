package org.household.inventory.categories.mappers;

import java.util.List;
import java.util.stream.Collectors;
import org.household.inventory.categories.dto.CategoryResponse;
import org.household.inventory.categories.dto.CreateCategoryRequest;
import org.household.inventory.categories.dto.CreateCategoryResponse;
import org.household.inventory.categories.dto.PaginatedCategoryResponse;
import org.household.inventory.categories.dto.UpdateCategoryRequest;
import org.household.inventory.categories.dto.UpdateCategoryResponse;
import org.household.inventory.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between Category entities and DTOs. Follows single responsibility
 * principle and provides clean separation between API layer and domain layer.
 */
@Component
public final class CategoriesMapper {

  /**
   * Converts CreateCategoryRequest DTO to Category entity.
   *
   * @param request the DTO containing category creation data
   * @return the Category entity
   * @throws IllegalArgumentException if request is null
   */
  public Category toEntity(CreateCategoryRequest request) {
    if (request == null) {
      throw new IllegalArgumentException("CreateCategoryRequest cannot be null");
    }

    Category category = new Category();
    category.setName(request.getName());
    category.setDescription(request.getDescription());

    return category;
  }

  /**
   * Converts Category entity to CreateCategoryResponse DTO.
   *
   * @param category the entity to convert
   * @return the DTO representation
   * @throws IllegalArgumentException if category is null
   */
  public CreateCategoryResponse toCreateResponse(Category category) {
    if (category == null) {
      throw new IllegalArgumentException("Category cannot be null");
    }

    return new CreateCategoryResponse(category.getId().toString(), category.getCreatedAt());
  }

  /**
   * Converts Category entity to CategoryResponse DTO.
   *
   * @param category the entity to convert
   * @return the DTO representation
   * @throws IllegalArgumentException if category is null
   */
  public CategoryResponse toResponse(Category category) {
    if (category == null) {
      throw new IllegalArgumentException("Category cannot be null");
    }

    return new CategoryResponse(
        category.getId(),
        category.getName(),
        category.getDescription(),
        category.getCreatedAt(),
        category.getCreatedBy(),
        category.getUpdatedAt(),
        category.getUpdatedBy());
  }

  /**
   * Converts UpdateCategoryRequest DTO to Category entity for partial updates. Only non-null fields
   * will be set on the entity.
   *
   * @param request the DTO containing category update data
   * @return the Category entity with update data
   * @throws IllegalArgumentException if request is null
   */
  public Category toUpdateEntity(UpdateCategoryRequest request) {
    if (request == null) {
      throw new IllegalArgumentException("UpdateCategoryRequest cannot be null");
    }

    Category category = new Category();
    category.setName(request.getName());
    category.setDescription(request.getDescription());

    return category;
  }

  /**
   * Converts Category entity to UpdateCategoryResponse record.
   *
   * @param category the entity to convert
   * @return the UpdateCategoryResponse record
   * @throws IllegalArgumentException if category is null
   */
  public UpdateCategoryResponse toUpdateResponse(Category category) {
    if (category == null) {
      throw new IllegalArgumentException("Category cannot be null");
    }

    return new UpdateCategoryResponse(
        category.getId(),
        category.getName(),
        category.getDescription(),
        category.getCreatedAt(),
        category.getCreatedBy(),
        category.getUpdatedAt(),
        category.getUpdatedBy());
  }

  public List<CategoryResponse> toResponseList(List<Category> all) {
    return all.stream().map(this::toResponse).collect(Collectors.toList());
  }

  /**
   * Converts Page<Category> to PaginatedCategoryResponse DTO.
   *
   * @param page the paginated entity data
   * @return the paginated DTO representation
   * @throws IllegalArgumentException if page is null
   */
  public PaginatedCategoryResponse toPaginatedResponse(Page<Category> page) {
    if (page == null) {
      throw new IllegalArgumentException("Page cannot be null");
    }

    List<CategoryResponse> categories =
        page.getContent().stream().map(this::toResponse).collect(Collectors.toList());

    return new PaginatedCategoryResponse(
        categories,
        page.getNumber(),
        page.getSize(),
        page.getTotalElements(),
        page.getTotalPages(),
        page.isFirst(),
        page.isLast(),
        page.hasNext(),
        page.hasPrevious());
  }
}
