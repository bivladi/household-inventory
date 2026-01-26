package org.household.inventory.categories.dto;

import java.util.List;

/**
 * DTO representing a paginated response for categories. Contains the list of categories along with
 * pagination metadata to help clients navigate through the results.
 */
public record PaginatedCategoryResponse(
    List<CategoryResponse> categories,
    int currentPage,
    int pageSize,
    long totalElements,
    int totalPages,
    boolean isFirst,
    boolean isLast,
    boolean hasNext,
    boolean hasPrevious) {}
