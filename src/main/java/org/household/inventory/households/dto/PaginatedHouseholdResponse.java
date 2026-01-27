package org.household.inventory.households.dto;

import java.util.List;

/**
 * DTO representing a paginated response for households. Contains the list of households along with
 * pagination metadata to help clients navigate through the results.
 */
public record PaginatedHouseholdResponse(
    List<HouseholdResponse> households,
    int currentPage,
    int pageSize,
    long totalElements,
    int totalPages,
    boolean isFirst,
    boolean isLast,
    boolean hasNext,
    boolean hasPrevious) {}
