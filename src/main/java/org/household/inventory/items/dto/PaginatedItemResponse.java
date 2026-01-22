package org.household.inventory.items.dto;

import java.util.List;

/**
 * DTO representing a paginated response for items. Contains the list of items along with pagination
 * metadata to help clients navigate through the results.
 */
public record PaginatedItemResponse(
    List<ItemResponse> items,
    int currentPage,
    int pageSize,
    long totalElements,
    int totalPages,
    boolean isFirst,
    boolean isLast,
    boolean hasNext,
    boolean hasPrevious) {}
