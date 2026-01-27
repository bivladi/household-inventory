package org.household.inventory.common.api.model;

import java.util.List;

/**
 * Generic DTO representing a paginated response for any type of data. Contains the list of data
 * along with pagination metadata to help clients navigate through the results.
 */
public record PaginatedResponse<T>(
    List<T> data,
    int currentPage,
    int pageSize,
    long totalElements,
    int totalPages,
    boolean isFirst,
    boolean isLast,
    boolean hasNext,
    boolean hasPrevious) {}
